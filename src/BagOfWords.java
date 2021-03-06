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
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BagOfWords {

    private Properties prop;
    private InputStream input = null;

    private ArrayList<String> bagOfWords;
    private File dirFiles;
    private List<File> files = new ArrayList<>();
    private List<File> textFolders = new ArrayList<>();
    private List<ArrayList<String>> listao = new ArrayList<>();
    private List<String> subjects = new ArrayList<>();
    private ArrayList<String> linhasTreino = new ArrayList<>();
    private ArrayList<String> linhasTeste = new ArrayList<>();

    public void Process() throws IOException {
        prop = new Properties();
        input = new FileInputStream(System.getProperty("user.dir") + "\\src\\config.properties");
        prop.load(input);

        System.out.println("Lendo BagOfWords:");
        readBagOfWords();

        System.out.println("Lendo Arquivos:");
        readTextFiles();

        for (final File fileEntry : textFolders) {
            if (fileEntry.isDirectory()) {
                listao = new ArrayList<>();
                for (File file : fileEntry.listFiles()) {
                    if (file.isFile()) {
                        listOriginalWord(file);
                    }
                }

                String assunto = getSubject(fileEntry);
                subjects.add(assunto);
                writeBinaryLine(assunto);
            }
        }
        writeARFF(linhasTreino, "treino");
        writeARFF(linhasTeste, "teste");
    }

    private void readBagOfWords() {
        //Cria list com as stopWords do arquivo
        bagOfWords = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(new File(prop.getProperty("pathBagOfWords")));

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha = "";
            while ((linha = bufferedReader.readLine()) != null) {
                bagOfWords.add(linha);
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

    private void writeBinaryLine(String assunto) {
        int sizeTreino = (int) Math.round(listao.size() - (listao.size() * 0.2));
        int cont = 1;

        for (ArrayList<String> wordsInText : listao) {
            String bin = "";

            int limit = getBagOfWordsLimit();
            //for (String wordInBag : bagOfWords.subList(0, limit)) {
            for (String wordInBag : bagOfWords) {
                if (wordsInText.contains(wordInBag)) {
                    bin += 1 + ",";
                } else {
                    bin += 0 + ",";
                }
            }

            bin += assunto;
            if (cont <= sizeTreino) {
                linhasTreino.add(bin);
            } else {
                linhasTeste.add(bin);
            }

            cont++;
        }
    }

    private void writeARFF(ArrayList<String> binario, String type) {
        try {
            FileWriter fw = new FileWriter(dirFiles + "\\" + type + ".arff", false);

            BufferedWriter bw = new BufferedWriter(fw);
            setFileHeaders(bw, type);

            for (String s : binario) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSubject(File fileEntry) {
        String[] pathSplit = fileEntry.getPath().split("\\\\"); //buf.readLine();
        return pathSplit[pathSplit.length - 1];
    }

    private void setFileHeaders(BufferedWriter bw, String type) {
        try {
            bw.write("@relation <tfIA2" + type + ">");
            bw.newLine();
            bw.newLine();

            int limit = getBagOfWordsLimit();
            //for (String attr : bagOfWords.subList(0, limit)) {
            for (String attr : bagOfWords) {
                bw.write("@attribute <" + attr + "> integer");
                bw.newLine();
            }

            String joined = "";
            for (int i = 0; i < subjects.size(); i++) {
                joined += subjects.get(i);
                if (i != subjects.size() - 1) {
                    joined += ",";
                }
            }

            bw.write("@attribute classes {" + joined + "}");
            bw.newLine();
            bw.newLine();
            bw.write("@data");
            bw.newLine();

        } catch (IOException ex) {
            Logger.getLogger(BagOfWords.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int getBagOfWordsLimit() {
        int size = Integer.parseInt(prop.getProperty("BAG_OF_WORDS_SIZE"));
        if (size == 0) {
            size = bagOfWords.size();
        }

        return size;
    }
}
