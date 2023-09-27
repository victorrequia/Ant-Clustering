package source.model;

import java.awt.Point;

public class Item {
    private Point ponto;
    private double dimensao_x;
    private double dimensao_y;

    // Colocar item no mapa
    public Item(int WIDTH, int HEIGHT, int UNIT_SIZE, Point point) {
        int x = point.x;
        int y = point.y;
        ponto = new Point(x, y);
        dimensao_x = Dimensao.dimensoes[Dimensao.count];
        dimensao_y = Dimensao.dimensoes[Dimensao.count + 1];
        Dimensao.count += 2;
    }

    public Item(int WIDTH, int HEIGHT, int UNIT_SIZE, Point point, double dimensao_x, double dimensao_y) {
        int x = point.x;
        int y = point.y;
        ponto = new Point(x, y);
        this.dimensao_x = dimensao_x;
        this.dimensao_y = dimensao_y;
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
