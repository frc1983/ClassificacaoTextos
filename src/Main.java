import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PreProcess pre = new PreProcess();
        BagOfWords loadBag = new BagOfWords();

        pre.Init();
        loadBag.Process();
    }

}
