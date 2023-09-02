package source;
import java.util.Random;

public class Formiga {
    private int x;
    private int y;

    public Formiga(int width, int height) {
        x = new Random().nextInt(width);
        y = new Random().nextInt(height);
    }

    public void acao(int width, int height){

    }
}
