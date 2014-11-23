
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author monica
 */
public class BagOfWords {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        String pathBag, pathDir,assunto;
        File dirFiles, bag;

        System.out.println("Caminho da BagOfWords:");
        //pathBag = buf.readLine();
        pathBag = "C:\\Users\\monica\\Desktop\\IA\\categorias_balanceadas\\bag_of_words_geral.txt";
        bag = new File(pathBag);

        System.out.println("Caminho dos Arquivos:");
        pathDir = buf.readLine();
        dirFiles = new File(pathDir);
        
        System.out.println("Nome assunto:");
        assunto = buf.readLine();

        List<File> files = new ArrayList<>();

        //Cria List com arquivos da pasta designada
        for (File file : dirFiles.listFiles()) {
            files.add(file);
        }
        
        //Cria list com as stopWords do arquivo
        ArrayList<String> bagList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(bag);

            BufferedReader bufferedReader
                    = new BufferedReader(fileReader);
            String linha = "";
            while ((linha = bufferedReader.readLine()) != null) {
                bagList.add(linha);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
        
        ArrayList<String> binario = new ArrayList<String>();
        
        for(ArrayList<String> f: listao){
            String bin = "";
            for(String p: f){
                
                if(bagList.contains(p)){
                    bin+=1+",";
                }else{
                    bin+=0+",";
                }
            }
            bin+=assunto;
            binario.add(bin);            
        }
        
        //Cria arquivo para alimentar o arff
        //cria arquivo de teste
        try {
            FileWriter fw = new FileWriter(pathDir + "linhas_"+assunto+".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

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
    
}
