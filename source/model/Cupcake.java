package source.model;

import java.awt.Point;
import java.util.Random;

public class Cupcake {
    private Point ponto;

    // Colocar item de forma aleatória no mapa
    public Cupcake(int WIDTH, int HEIGHT, int UNIT_SIZE) {
        int x = new Random().nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        int y = new Random().nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        ponto = new Point(x, y);
    }

    // Colocar item de forma pontual no mapa
    public Cupcake(int WIDTH, int HEIGHT, int UNIT_SIZE, Point point) {
        int x = point.x;
        int y = point.y;
        ponto = new Point(x, y);
    }

    // Getters e Setters
    public Point getPonto() {
        return ponto;
    }

    public void setPontos(Point ponto) {
        this.ponto = ponto;
    }
}
