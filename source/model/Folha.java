package source.model;

import java.awt.Point;

public class Folha extends Item{
    public Folha(int WIDTH, int HEIGHT, int UNIT_SIZE) {
        super(WIDTH, HEIGHT, UNIT_SIZE);
    }

    public Folha(int WIDTH, int HEIGHT, int UNIT_SIZE, Point point) {
        super(WIDTH, HEIGHT, UNIT_SIZE, point);
    }
}
