package source.model;

import java.awt.Point;

public class Cupcake extends Item {

    public Cupcake(int WIDTH, int HEIGHT, int UNIT_SIZE) {
        super(WIDTH, HEIGHT, UNIT_SIZE);
    }

    public Cupcake(int WIDTH, int HEIGHT, int UNIT_SIZE, Point point) {
        super(WIDTH, HEIGHT, UNIT_SIZE, point);
    }

}
