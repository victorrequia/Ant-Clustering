package source.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import source.model.Formiga;
import source.model.Item;

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
        Item item;
        int i = 0;
        int count;

        if (itens.isEmpty()) {
            item = new Item(WIDTH, HEIGHT, UNIT_SIZE);
            itens.add(item);
        }

        while (i < quantidadeItens - 1) {
            count = 0;
            item = new Item(WIDTH, HEIGHT, UNIT_SIZE);
            for (int j = 0; j < itens.size(); j++) {
                if (itens.get(j).getPonto().getX() == item.getPonto().getX()
                        && itens.get(j).getPonto().getY() == item.getPonto().getY()) {
                    count++;
                    break;
                }
            }
            if (count == 0) {
                itens.add(item);
                i++;
            }
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
        Iterator<Formiga> formigaIterator = formigas.iterator();
        while (formigaIterator.hasNext()) {
            Iterator<Item> itemIterator = itens.iterator();
            Formiga formiga = formigaIterator.next();
            Point head = formiga.getPontos().get(0);

            while (itemIterator.hasNext()) {
                Item item = itemIterator.next();
                boolean itemPickedUp = false;

                if (item.getPonto().getX() == head.getX() && item.getPonto().getY() == head.getY()) {
                    int soma = quantidadeItensProximos(itens, head);
                    float chance = (float) (1 - soma / 8.0);
                    if (random.nextFloat() < chance) {
                        carregando.add(formiga);
                        formigaIterator.remove();
                        itemIterator.remove();
                        itemPickedUp = true;
                    }
                    break;
                }
                if (itemPickedUp)
                    break;
            }
        }
    }

    public void decisaoLargar(ArrayList<Formiga> formigas, ArrayList<Item> itens, ArrayList<Formiga> formigasCarregando) {
        Iterator<Formiga> formigaCarregandoIterator = formigasCarregando.iterator();
        int count = 0;
        while (formigaCarregandoIterator.hasNext()) {
            Formiga formiga = formigaCarregandoIterator.next();
            count = 0;
            Point head = formiga.getPontos().get(0);
            for (int i = 0; i < itens.size(); i++) {
                if (itens.get(i).getPonto().getX() == head.getX() && itens.get(i).getPonto().getY() == head.getY()) {
                    count++;
                    break;
                }
            }
            if (count == 0) {
                int soma = quantidadeItensProximos(itens, head);
                float chance = (float) soma / 8;
                if (random.nextFloat() < chance) {
                    itens.add(new Item(WIDTH, HEIGHT, UNIT_SIZE, head));
                    formigaCarregandoIterator.remove();
                    formigas.add(new Formiga(WIDTH, HEIGHT, UNIT_SIZE, formiga.getPontos().get(0)));
                }
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
            for (int[] direction : directions) {
                if (item.getPonto().x + direction[0] == head.x && item.getPonto().y + direction[1] == head.y) {
                    soma += 1;
                }
            }
        }
        return soma;
    }
}
