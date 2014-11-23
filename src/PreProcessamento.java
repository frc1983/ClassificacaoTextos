import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

public class PreProcessamento {
    private static Properties prop;
    private static InputStream input = null;

    private static File dirFiles;
    private static List<File> textFolders = new ArrayList<>();
    private static ArrayList<String> listStopWords = new ArrayList<>();
    private static List<ArrayList<String>> listao = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        prop = new Properties();
        input = new FileInputStream(System.getProperty("user.dir") + "\\src\\config.properties");
        prop.load(input);

        System.out.println("Lendo StopWords:");
        readStopListFile();

        System.out.println("Lendo Arquivos:");
        readTextFiles();

        for (final File fileEntry : textFolders) {
            if (fileEntry.isDirectory()) {
                makeProcess(Arrays.asList(fileEntry.listFiles()));
            }
        }
    }

    public static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }

    public static File createDirectory(String path) {
        try {
            File novaPasta = new File(path);
            if (novaPasta.exists()) {
                if (deleteDirectory(novaPasta)) {
                    novaPasta.mkdir();
                    novaPasta.setWritable(true);
                    novaPasta.setReadable(true);
                    novaPasta.setExecutable(true);
                }
            }

            return novaPasta;
        } catch (Exception e) {
            return null;
        }
    }

    private static void readStopListFile() {
        //Cria list com as stopWords do arquivo
        try {
            FileReader fileReader = new FileReader(new File(prop.getProperty("pathStopList")));

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha = "";
            while ((linha = bufferedReader.readLine()) != null) {
                listStopWords.add(linha);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readTextFiles() {
        dirFiles = new File(prop.getProperty("pathSubjects"));
        for (final File fileEntry : dirFiles.listFiles()) {
            if (fileEntry.isDirectory()) {
                textFolders.add(fileEntry);
            }
        }
    }

    private static void listOriginalWord(File file) {
        ArrayList<String> arqFiles = new ArrayList<>();
        try {
            BufferedReader bufferedReader;
            try (FileReader fileReader = new FileReader(file)) {
                bufferedReader = new BufferedReader(fileReader);
                String linha = "";
                while ((linha = bufferedReader.readLine()) != null) {
                    linha = linha.replaceAll("[^A-Za-z ]", "");

                    arqFiles.add(linha);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listao.add(arqFiles);
    }

    private static ArrayList<String> createClearedFiles(File novaPasta) {
        int nomeArq = 1;
        ArrayList<String> allFiles = new ArrayList<>();

        for (ArrayList<String> l : listao) {
            //arquivos menos listStopWords
            l.removeAll(listStopWords);
            //cria arquivo de teste
            try {
                FileWriter fw = new FileWriter(novaPasta + "\\" + String.valueOf(nomeArq) + ".txt", true);
                nomeArq++;
                BufferedWriter bw = new BufferedWriter(fw);

                for (String s : l) {

                    if (!s.isEmpty() && !s.equals("")) {
                        allFiles.add(s);
                        bw.write(s);
                        bw.newLine();
                    }
                }
                bw.close();
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return allFiles;
    }

    private static void makeRankFile(File novaPasta, ArrayList<String> allFiles, ArrayList<String> allFilesUnique) {
        ArrayList<String> rank = new ArrayList<>();

        for (int i = 0; i < allFilesUnique.size(); i++) {
            rank.add(allFilesUnique.get(i) + ";" + Collections.frequency(allFiles, allFilesUnique.get(i)));
        }

        try {
            FileWriter fw = new FileWriter(novaPasta + "\\" + "ranking.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String s : rank) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeProcess(List<File> files) {
        HashSet<String> allFilesUniqueHash = new HashSet<>();
        ArrayList<String> allFilesUnique = new ArrayList<>();

        //Crias lista de lista de Strings com todos os arquivos da pasta designada        
        for (File file : files) {
            if (file.isFile()) {
                listOriginalWord(file);
            }
        }

        //Cria pasta com textos sem stopwords
        File novaPasta = createDirectory(files.get(0).getPath());

        //Cria os arquivos de texto com palavras relevantes - sem stopwords
        ArrayList<String> allFiles = createClearedFiles(novaPasta);
        allFilesUniqueHash.addAll(allFiles);
        Collections.sort(allFilesUnique);
        allFilesUnique.addAll(allFilesUniqueHash);

        makeRankFile(novaPasta, allFiles, allFilesUnique);
    }
}
