package source.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Formiga {
    private ArrayList<Point> pontos = new ArrayList<>();

    // Colocar formida de forma aleatória no mapa
    public Formiga(int WIDTH, int HEIGHT, int UNIT_SIZE) {
        int x = new Random().nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        int y = new Random().nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        pontos.add(new Point(x, y));
    }

    // Colocar formida de forma pontual no mapa
    public Formiga(int WIDTH, int HEIGHT, int UNIT_SIZE, Point point) {
        int x = point.x;
        int y = point.y;
        pontos.add(new Point(x, y));
    }

    // Getters e Setters
    public ArrayList<Point> getPontos() {
        return pontos;
    }

    public void setPontos(ArrayList<Point> pontos) {
        this.pontos = pontos;
    }
}
