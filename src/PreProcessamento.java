import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

public class PreProcessamento {

    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        String pathStopList, pathDir;
        File dirFiles, stopList;

        System.out.println("Caminho da StopWords:");
        pathStopList = buf.readLine();
        stopList = new File(pathStopList);

        System.out.println("Caminho dos Arquivos:");
        pathDir = buf.readLine();
        dirFiles = new File(pathDir);

        List<File> files = new ArrayList<>();

        //Cria List com arquivos da pasta designada
        for (File file : dirFiles.listFiles()) {
            files.add(file);
        }

        //Cria list com as stopWords do arquivo
        ArrayList<String> listStopWords = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(stopList);

            BufferedReader bufferedReader
                    = new BufferedReader(fileReader);
            String linha = "";
            while ((linha = bufferedReader.readLine()) != null) {
                listStopWords.add(linha);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Crias lista de lista de Strings com todos os arquivos da pasta designada
        List<ArrayList<String>> listao = new ArrayList<>();

        for (File file : files) {
            ArrayList<String> arqFiles = new ArrayList<>();
            try {
                FileReader fileReader = new FileReader(file);

                BufferedReader bufferedReader
                        = new BufferedReader(fileReader);
                String linha = "";
                while ((linha = bufferedReader.readLine()) != null) {
                    linha = linha.replaceAll("[^A-Za-z]", "");

                    arqFiles.add(linha);
                }
                fileReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            listao.add(arqFiles);
        }

        File novaPasta = new File(dirFiles + "\\retorno");
        if(novaPasta.exists()){
            novaPasta.delete();
        }
        novaPasta.mkdir();
        int nomeArq = 1;
        ArrayList<String> allFiles = new ArrayList<String>();
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

    Collections.sort (allFiles);

    ArrayList<String> rank = new ArrayList<String>();

    HashSet<String> allFilesUniqueHash = new HashSet<String>();

    allFilesUniqueHash.addAll (allFiles);

    ArrayList<String> allFilesUnique = new ArrayList<String>();
    
    Collections.sort(allFilesUnique);

    allFilesUnique.addAll (allFilesUniqueHash);

    for (int i = 0;i< allFilesUnique.size ();i++) {
            int cont = 0;
        /*for (int j = 0; j <= allFiles.size(); j++) {
            if (allFilesUnique.get(i).equals(allFiles.get(j))) {
                Collections.
                cont++;
            } 
        }*/
        rank.add(allFilesUnique.get(i) + ";" + Collections.frequency(allFiles, allFilesUnique.get(i)));

    }
    //rank.toString();
    
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

}
