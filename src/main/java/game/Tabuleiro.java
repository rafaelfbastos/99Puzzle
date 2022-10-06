package game;

import database.SettingsModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Tabuleiro {

    private ArrayList<Integer> tabuleiro;
    private ArrayList<Integer> ordenado;
    private ArrayList<Integer> limiteDireita;
    private ArrayList<Integer> limiteEsquerda;
    private int[][] matrixDePosicoes;

    private SettingsModel settings;

    public Tabuleiro() {

        settings = Recursos.getInstance().getSettings();

        ordenado = new ArrayList<>();
        matrixDePosicoes = new int[settings.getTamanho()][settings.getTamanho()];


        for (int i = 0; i < settings.getnPartes(); i++) {
            ordenado.add(i);
        }
        int k = 0;
        for (int i = 0; i < settings.getTamanho(); i++) {
            for (int j = 0; j < settings.getTamanho(); j++) {
                matrixDePosicoes[i][j] = ordenado.get(k);
                k++;
            }
        }
        ordenado.remove(0);
        ordenado.add(0);
        tabuleiro = new ArrayList<>(ordenado);
        limiteDireita = new ArrayList<>();
        limiteEsquerda = new ArrayList<>();

        limiteEsquerda.add(0);
        limiteDireita.add(settings.getTamanho()-1);
        for (int i = 0; i <(settings.getTamanho()-1); i++) {
            limiteDireita.add(limiteDireita.get(i)+settings.getTamanho());
            limiteEsquerda.add(limiteEsquerda.get(i)+settings.getTamanho());
        }

        shuffleTabuleiro();

    }

    public ArrayList<Integer> getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(ArrayList<Integer> tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public int[][] getMatrixDePosicoes() {
        return matrixDePosicoes;
    }

    public void setMatrixDePosicoes(int[][] matrixDePosicoes) {
        this.matrixDePosicoes = matrixDePosicoes;
    }

    public int clickedPosition(int mouseX, int mouseY) {
        int x = 0;
        int y = 0;
        int incremento = settings.getDimensao()+settings.getEspacamento();
        int tamanhoMaximo = incremento*settings.getTamanho();

        for (int i = 0; i <tamanhoMaximo; i = i + incremento) {
            if (mouseX > i && mouseX < i + incremento) break;
            x++;
        }
        for (int i = 0; i < tamanhoMaximo; i = i + incremento) {
            if (mouseY > i && mouseY < i + incremento) break;
            y++;
        }
        return matrixDePosicoes[y][x];
    }
    public void swapPecas(int clicada){


        //mover direita
        if(clicada+1<settings.getnPartes() && tabuleiro.get(clicada+1)==0 && !limiteEsquerda.contains(clicada+1)){
            Collections.swap(tabuleiro,clicada,clicada+1);
            Recursos.getInstance().incrementarJogadas();
            Recursos.getInstance().tocarSwap();
        }
        //mover esquerda
        if(clicada-1>=0 && tabuleiro.get(clicada-1)==0 && !limiteDireita.contains(clicada-1)){
            Collections.swap(tabuleiro,clicada,clicada-1);
            Recursos.getInstance().incrementarJogadas();
            Recursos.getInstance().tocarSwap();
        }

        //mover cima
        if(clicada-settings.getTamanho()>=0 && tabuleiro.get(clicada-settings.getTamanho())==0){
            Collections.swap(tabuleiro,clicada,clicada-settings.getTamanho());
            Recursos.getInstance().incrementarJogadas();
            Recursos.getInstance().tocarSwap();
        }
        //mover baixo
        if(clicada+settings.getTamanho()<=(settings.getnPartes()-1) && tabuleiro.get(clicada+settings.getTamanho())==0){
            Collections.swap(tabuleiro,clicada,clicada+settings.getTamanho());
            Recursos.getInstance().incrementarJogadas();
            Recursos.getInstance().tocarSwap();
        }

    }
    public void shuffleTabuleiro(){


        for (int i = 0; i <1000000 ; i++) {
            int clicada = Recursos.getInstance().gerarRandom(settings.getnPartes());

            //mover direita
            if(clicada+1<settings.getnPartes() && tabuleiro.get(clicada+1)==0 && !limiteEsquerda.contains(clicada+1)){
                Collections.swap(tabuleiro,clicada,clicada+1);
            }
            //mover esquerda
            if(clicada-1>=0 && tabuleiro.get(clicada-1)==0 && !limiteDireita.contains(clicada-1)){
                Collections.swap(tabuleiro,clicada,clicada-1);
            }

            //mover cima
            if(clicada-settings.getTamanho()>=0 && tabuleiro.get(clicada-settings.getTamanho())==0){
                Collections.swap(tabuleiro,clicada,clicada-settings.getTamanho());
            }
            //mover baixo
            if(clicada+settings.getTamanho()<=(settings.getnPartes()-1) && tabuleiro.get(clicada+settings.getTamanho())==0){
                Collections.swap(tabuleiro,clicada,clicada+settings.getTamanho());
            }
        }

    }
    public boolean verificarVitoria(){

        return tabuleiro.equals(ordenado);
    }

    public void remontarTabuleiro(){
        settings = Recursos.getInstance().getSettings();

        ordenado = new ArrayList<>();
        matrixDePosicoes = new int[settings.getTamanho()][settings.getTamanho()];


        for (int i = 0; i < settings.getnPartes(); i++) {
            ordenado.add(i);
        }
        int k = 0;
        for (int i = 0; i < settings.getTamanho(); i++) {
            for (int j = 0; j < settings.getTamanho(); j++) {
                matrixDePosicoes[i][j] = ordenado.get(k);
                k++;
            }
        }
        ordenado.remove(0);
        ordenado.add(0);
        tabuleiro = new ArrayList<>(ordenado);
        limiteDireita = new ArrayList<>();
        limiteEsquerda = new ArrayList<>();

        limiteEsquerda.add(0);
        limiteDireita.add(settings.getTamanho()-1);
        for (int i = 0; i <(settings.getTamanho()-1); i++) {
            limiteDireita.add(limiteDireita.get(i)+settings.getTamanho());
            limiteEsquerda.add(limiteEsquerda.get(i)+settings.getTamanho());
        }

        shuffleTabuleiro();
    }

    public ArrayList<Integer> getOrdenado() {
        return ordenado;
    }
}
