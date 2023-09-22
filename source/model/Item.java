package source.model;

import java.awt.Point;
import java.util.Random;

public class Item {
    private Point ponto;
    private double dimensao_x;
    private double dimensao_y;

    // Colocar item de forma aleatória no mapa
    public Item(int WIDTH, int HEIGHT, int UNIT_SIZE, double dimensao_x, double dimensao_y) {
        this.dimensao_x = dimensao_x;
        this.dimensao_y = dimensao_y;
        int x = new Random().nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        int y = new Random().nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        ponto = new Point(x, y);
    }

    // Colocar item de forma pontual no mapa
    public Item(int WIDTH, int HEIGHT, int UNIT_SIZE, Point point) {
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

    public double getDimensao_x() {
        return dimensao_x;
    }

    public void setDimensao_x(int dimensao_x) {
        this.dimensao_x = dimensao_x;
    }

    public double getDimensao_y() {
        return dimensao_y;
    }

    public void setDimensao_y(int dimensao_y) {
        this.dimensao_y = dimensao_y;
    }

}
