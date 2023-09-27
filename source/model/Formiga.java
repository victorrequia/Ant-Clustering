package source.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Formiga {
    private ArrayList<Point> pontos = new ArrayList<>();
    private double dimensao_x;
    private double dimensao_y;;

    // Colocar formida de forma aleatória no mapa
    public Formiga(int WIDTH, int HEIGHT, int UNIT_SIZE) {
        int x = new Random().nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        int y = new Random().nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        pontos.add(new Point(x, y));
    }

    // Colocar formida quando coleta um item
    public Formiga(int WIDTH, int HEIGHT, int UNIT_SIZE, Point point, int dimensao_x, int dimensao_y) {
        int x = point.x;
        int y = point.y;
        this.dimensao_x = dimensao_x;
        this.dimensao_y = dimensao_y;
        pontos.add(new Point(x, y));
    }

    // Colocar formida quando dropa um item
    public Formiga(int WIDTH, int HEIGHT, int UNIT_SIZE, Point point) {
        int x = point.x;
        int y = point.y;
        pontos.add(new Point(x, y));
    }

    public double getDimensao_x() {
        return dimensao_x;
    }

    public void setDimensao_x(double dimensao_x) {
        this.dimensao_x = dimensao_x;
    }

    public double getDimensao_y() {
        return dimensao_y;
    }

    public void setDimensao_y(double dimensao_y) {
        this.dimensao_y = dimensao_y;
    }

    // Getters e Setters
    public ArrayList<Point> getPontos() {
        return pontos;
    }

    public void setPontos(ArrayList<Point> pontos) {
        this.pontos = pontos;
    }
}
