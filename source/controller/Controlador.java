package source.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import source.model.*;

public class Controlador {
    private Random random = new Random();
    private char[] direcoes = { 'U', 'D', 'L', 'R' };

    public void criarFormigas(ArrayList<Formiga> itens, int WIDTH, int HEIGHT, int UNIT_SIZE) {
        for (int i = 0; i < 10; i++) {
            itens.add(new Formiga(WIDTH, HEIGHT, UNIT_SIZE));
        }
    }

    public void criarItens(ArrayList<Item> itens, int WIDTH, int HEIGHT, int UNIT_SIZE) {
        for (int i = 0; i < 10; i++) {
            itens.add(new Item(WIDTH, HEIGHT, UNIT_SIZE));
        }
    }

    public char move(Point head, char direcao, int UNIT_SIZE, int HEIGHT, int WIDTH){
        switch (direcao) {
            case 'U':
                if (head.getY() > 0) {
                    head.y -= UNIT_SIZE;
                }
                break;
            case 'D':
                if (head.getY() < HEIGHT - 20) {
                    head.y += UNIT_SIZE;
                }
                break;
            case 'L':
                if (head.getX() > 0) {
                    head.x -= UNIT_SIZE;
                }
                break;
            case 'R':
                if (head.getX() < WIDTH - 20) {
                    head.x += UNIT_SIZE;
                }
                break;
        }
        int indiceSorteado = random.nextInt(direcoes.length);
        direcao = direcoes[indiceSorteado];
        return direcao;
    }
}
