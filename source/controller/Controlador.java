package source.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import source.model.Formiga;
import source.model.Item;
import source.view.SnakeGame;

public class Controlador {
    private Random random = new Random();
    private char[] direcoes = { 'U', 'D', 'L', 'R' };

    public void criarFormigas(ArrayList<Formiga> itens, int WIDTH, int HEIGHT, int UNIT_SIZE) {
        for (int i = 0; i < 3; i++) {
            itens.add(new Formiga(WIDTH, HEIGHT, UNIT_SIZE));
        }
    }

    public void criarItens(ArrayList<Item> itens, int WIDTH, int HEIGHT, int UNIT_SIZE) {
        for (int i = 0; i < 50; i++) {
            itens.add(new Item(WIDTH, HEIGHT, UNIT_SIZE));
        }
    }

    public char move(Point head, char direcao, int UNIT_SIZE, int HEIGHT, int WIDTH) {
        switch (direcao) {

            case 'U':
                if (head.getY() > 0) {

                    head.y -= UNIT_SIZE;
                }

                if (head.getY() == 0) {
                    head.y = HEIGHT - 20;
                }
                break;

            case 'D':
                if (head.getY() < HEIGHT - UNIT_SIZE) {
                    head.y += UNIT_SIZE;
                }
                if (head.getY() == HEIGHT - 20) {
                    head.y = 0;
                }
                break;

            case 'L':
                if (head.getX() > 0) {
                    head.x -= UNIT_SIZE;
                }

                if (head.getX() == 0) {
                    head.x = WIDTH - 20;
                }
                break;

            case 'R':
                if (head.getX() < WIDTH - UNIT_SIZE) {
                    head.x += UNIT_SIZE;
                }

                if (head.getX() == WIDTH - UNIT_SIZE) {
                    head.x = 0;
                }
                break;

        }
        int indiceSorteado = random.nextInt(direcoes.length);
        direcao = direcoes[indiceSorteado];
        return direcao;
    }

    public void decisao(Formiga formiga, ArrayList<Item> itens, Point head) {
        Iterator<Item> itemIterator = itens.iterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            for (Point point : item.getPontos()) {
                if (point.getX() == head.getX() && point.getY() == head.getY()) {
                    int soma = quantidadeItensProximos(itens, head);
                    System.out.println("Soma: " + soma);
                    //SnakeGame.carregando.add(formiga);
                    //itemIterator.remove();
                }
            }
        }
    }

    public int quantidadeItensProximos(ArrayList<Item> itens, Point head) {
        int soma = 0;
    
        // Possible relative positions to check
        int[][] directions = {
            {-20, 0},  // Up
            {20, 0},   // Down
            {0, -20},  // Left
            {0, 20},   // Right
            {-20, -20}, // Upper left
            {20, -20},  // Lower left
            {-20, 20},  // Upper right
            {20, 20}    // Lower right
        };
    
        for (Item item : itens) {
            for (Point point : item.getPontos()) {
                for (int[] direction : directions) {
                    if (point.x + direction[0] == head.x && point.y + direction[1] == head.y) {
                        soma += 1;
                    }
                }
            }
        }
    
        return soma;
    }
}
