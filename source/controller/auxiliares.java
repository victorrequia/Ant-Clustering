package source.controller;

import java.awt.Point;
import java.util.Random;

public class auxiliares {
    // Gerar ponto aleatorio
    public static Point coordenadasPoint(int WIDTH, int HEIGHT, int UNIT_SIZE) {
        int x = new Random().nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        int y = new Random().nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        Point ponto = new Point(x, y);
        return ponto;
    }
}
