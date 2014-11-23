import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.K;
import static javafx.scene.input.KeyCode.V;

public class PreProcess {

    private Properties prop;
    private InputStream input = null;

    private File dirFiles;
    private List<File> textFolders = new ArrayList<>();
    private ArrayList<String> listStopWords = new ArrayList<>();
    private List<ArrayList<String>> listao = new ArrayList<>();

    public void Init() {
        prop = new Properties();
        try {
            input = new FileInputStream(System.getProperty("user.dir") + "\\src\\config.properties");
            prop.load(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PreProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PreProcess.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Lendo StopWords:");
        readStopListFile();

        System.out.println("Lendo Arquivos:");
        readTextFiles();

        for (final File fileEntry : textFolders) {
            if (fileEntry.isDirectory()) {
                makeProcess(Arrays.asList(fileEntry.listFiles()));
            }
        }

        makeGenericBagOfWords();
    }

    public boolean deleteDirectory(File path) {
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

    public File createDirectory(String path) {
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

    private void readStopListFile() {
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

    private void readTextFiles() {
        dirFiles = new File(prop.getProperty("pathSubjects"));
        for (final File fileEntry : dirFiles.listFiles()) {
            if (fileEntry.isDirectory()) {
                textFolders.add(fileEntry);
            }
        }
    }

    private void listOriginalWord(File file) {
        ArrayList<String> arqFiles = new ArrayList<>();
        try {
            BufferedReader bufferedReader;
            try (FileReader fileReader = new FileReader(file)) {
                bufferedReader = new BufferedReader(fileReader);
                String linha = "";
                while ((linha = bufferedReader.readLine()) != null) {
                    linha = linha.replaceAll("[^A-Za-z]", "");

                    arqFiles.add(linha);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listao.add(arqFiles);
    }

    private ArrayList<String> createClearedFiles(File novaPasta) {
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

    private void makeRankFile(File novaPasta, ArrayList<String> allFiles, ArrayList<String> allFilesUnique) {
        ArrayList<String> rank = new ArrayList<>();

        for (int i = 0; i < allFilesUnique.size(); i++) {
            rank.add(allFilesUnique.get(i) + ";" + Collections.frequency(allFiles, allFilesUnique.get(i)));
        }

        try {
            FileWriter fw = new FileWriter(prop.getProperty("pathSubjects") + "\\" + "ranking.txt", true);
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

    private void makeProcess(List<File> files) {
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

    private void makeGenericBagOfWords() {
        Map<String, Integer> rank = new TreeMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(prop.getProperty("pathSubjects") + "\\" + "ranking.txt"));

            String read;
            while ((read = reader.readLine()) != null) {
                String[] parts = read.split(";");
                if (rank.containsKey(parts[0])) {
                    int get = rank.get(parts[0]);
                    rank.put(parts[0], get + Integer.parseInt(parts[1]));
                } else {
                    rank.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
            rank = sortByComparator(rank);

            makeGenericBagOfWordsFile(rank);
   
            int i = 0;
            //TODO: Ler o arquivo e colocar em ordem de ranking maior > menor
            //pegar a quantidade de palavras do prop.getProperty("BAG_OF_WORDS_SIZE") e
            //gravar as palavras no arquivo bag_of_words_geral.txt
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PreProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PreProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        
        Collections.reverse(list);

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    private void makeGenericBagOfWordsFile(Map<String, Integer> ranked) {
        try {
            FileWriter fw = new FileWriter(prop.getProperty("pathBagOfWords"), false);
            BufferedWriter bw = new BufferedWriter(fw);

            int sizeOfBag = 0;
            for (String s : ranked.keySet()) {
                if(Integer.parseInt(prop.getProperty("BAG_OF_WORDS_SIZE")) != 0 && 
                        sizeOfBag == Integer.parseInt(prop.getProperty("BAG_OF_WORDS_SIZE"))){
                    break;
                }
                bw.write(s);
                bw.newLine();
                sizeOfBag++;
            }
            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
