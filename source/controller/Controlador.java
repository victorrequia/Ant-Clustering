package source.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import source.model.*;

public class Controlador {
    private Random random = new Random();
    private char[] direcoes = { 'U', 'D', 'L', 'R' };
    private int WIDTH;
    private int HEIGHT;
    private int UNIT_SIZE;

    // Construtor
    public Controlador(int WIDTH, int HEIGHT, int UNIT_SIZE) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.UNIT_SIZE = UNIT_SIZE;
    }

    // Colocar formigas de forma aleatorio no mapa
    public void criarFormigas(ArrayList<Formiga> formigas, int quantidadeFormigas) {
        for (int i = 0; i < quantidadeFormigas; i++) {
            formigas.add(new Formiga(WIDTH, HEIGHT, UNIT_SIZE));
        }
    }

    // Colocar itens de forma aleatorio no mapa
    public void criarItens(ArrayList<Item> itens, int quantidadeItens) {
        for (int i = 0; i < quantidadeItens; i++) {
            itens.add(new Item(WIDTH, HEIGHT, UNIT_SIZE));
        }
    }

    // Movimentar a formiga
    public char move(ArrayList<Formiga> formigas, char direcao) {
        for (Formiga formiga : formigas) {
            Point head = formiga.getPontos().get(0);
            switch (direcao) {
                case 'U':
                    if (head.getY() > 0) {

                        head.y -= UNIT_SIZE;
                    }
                    if (head.getY() == 0) {
                        head.y = HEIGHT - UNIT_SIZE;
                    }
                    break;
                case 'D':
                    if (head.getY() < HEIGHT - UNIT_SIZE) {
                        head.y += UNIT_SIZE;
                    }
                    if (head.getY() == HEIGHT - UNIT_SIZE) {
                        head.y = 0;
                    }
                    break;
                case 'L':
                    if (head.getX() > 0) {
                        head.x -= UNIT_SIZE;
                    }
                    if (head.getX() == 0) {
                        head.x = WIDTH - UNIT_SIZE;
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
        }
        return direcao;
    }

    // Decidir se a formiga pega o item
    public void decisaoPegar(ArrayList<Formiga> formigas, ArrayList<Formiga> carregando, ArrayList<Item> itens) {
        for (Formiga formiga : formigas) {
            Point head = formiga.getPontos().get(0);
            Iterator<Item> itemIterator = itens.iterator();
            while (itemIterator.hasNext()) {
                Item item = itemIterator.next();
                for (Point point : item.getPontos()) {
                    if (point.getX() == head.getX() && point.getY() == head.getY()) {
                        int soma = quantidadeItensProximos(itens, head);
                        System.out.println("Soma: " + soma);

                        float chance = (float) (1 - soma / 8.0);
                        System.out.println("Chance " + chance);
                        if (random.nextFloat() < chance) {
                            carregando.add(formiga);
                            itemIterator.remove();
                        }
                        break;
                    }
                }
            }
        }
    }

    public void decisaoLargar(ArrayList<Item> itens, ArrayList<Formiga> formigasCarregando) {
        Iterator<Formiga> formigaCarregandoIterator = formigasCarregando.iterator();

        while (formigaCarregandoIterator.hasNext()) {
            Formiga formiga = formigaCarregandoIterator.next();
            Point head = formiga.getPontos().get(0);
            int soma = quantidadeItensProximos(itens, head);
            float chance = (float) soma / 8;
            System.out.println("Chance de largar " + chance);
            if (random.nextFloat() < chance) {
                itens.add(new Item(WIDTH, HEIGHT, UNIT_SIZE, head));
                formigaCarregandoIterator.remove();
            }

        }
    }

    public int quantidadeItensProximos(ArrayList<Item> itens, Point head) {
        int soma = 0;

        int[][] directions = {
                { -UNIT_SIZE, 0 }, // Cima
                { UNIT_SIZE, 0 }, // Baixo
                { 0, -UNIT_SIZE }, // Esquerda
                { 0, UNIT_SIZE }, // Direita
                { -UNIT_SIZE, -UNIT_SIZE }, // Superior Esquerdo
                { UNIT_SIZE, -UNIT_SIZE }, // Inferior Esquerdo
                { -UNIT_SIZE, UNIT_SIZE }, // Superior Direito
                { UNIT_SIZE, UNIT_SIZE } // Inferior Direito
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
