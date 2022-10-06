package game;

import database.ScoreModel;
import database.SettingsDAO;
import database.SettingsModel;
import database.TimeModel;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class Recursos {

    private static Recursos singleton;
    private BufferedImage sprite;
    private BufferedImage sprite2;
    private Random rdn;

    private SettingsModel settings;

    private String estado;

    private Font tituloFont;
    private Font subTituloFont;
    private Font textFont;
    private Font buttonFont;
    private ScoreModel jogador;
    private TimeModel tempo;
    private int jogadas;
    private String modoJogo;
    private BufferedImage imgDefault;
    private String board;
    private AudioInputStream silence;
    private AudioInputStream swapPecas;
    private AudioInputStream win;
    private Clip silenceClip;
    private Clip swapPecasClip;
    private Clip winClip;
    private Image icon;
    private ArrayList<String> boards;
    private BufferedImage[] miniaturas;


    public Recursos(){
        boards = new ArrayList<>(
                Arrays.asList("Darth Vader","Lion","MK Logo","Pikachu","Sub-Zero","Yoda","Luffy",
                        "Darth Vader Helmet","Death Star","Naruto","Iron Man","Dragon")
        );
        miniaturas = new BufferedImage[boards.size()];
        jogador = new ScoreModel();
        modoJogo = "numerico";
        jogadas = 0;

        tituloFont = new Font("Consolas", Font.CENTER_BASELINE, 60);
        subTituloFont = new Font("Baskerville", Font.BOLD, 30);
        textFont = new Font("Candara", Font.CENTER_BASELINE, 24);
        buttonFont = new Font(" Century Gothic", Font.BOLD, 18);

        estado = "inicio";
        settings = SettingsDAO.getSettings("15Puzzle");

        rdn = new Random();



        try {
            sprite = ImageIO.read(getClass().getResource(settings.getPath()+ "/0.png"));
            sprite2 = ImageIO.read(getClass().getResource(settings.getPath()+ "/1.png"));
            imgDefault= ImageIO.read(getClass().getResource("/img/default.png"));
            silence = AudioSystem.getAudioInputStream(getClass().getResource("/audios/silence.wav"));
            win = AudioSystem.getAudioInputStream(getClass().getResource("/audios/win.wav"));
            swapPecas = AudioSystem.getAudioInputStream(getClass().getResource("/audios/transition.wav"));
            icon = ImageIO.read(getClass().getResource("/img/icon.png"));
            silenceClip = AudioSystem.getClip();
            winClip = AudioSystem.getClip();
            swapPecasClip = AudioSystem.getClip();
            silenceClip.open(silence);
            winClip.open(win);
            swapPecasClip.open(swapPecas);
            silenceClip.start();

            for (int i = 0; i <boards.size() ; i++) {
              miniaturas[i] = ImageIO.read(getClass().getResource(String.format("/img/%s.png",boards.get(i))));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

    }

    public static Recursos getInstance() {
        if(singleton==null) singleton = new Recursos();

        return singleton;
    }

    public BufferedImage imagemCortada(int numero){

        if(numero==0) return imgDefault.getSubimage(settings.getDimensao()*(settings.getTamanho()-1),settings.getDimensao()*(settings.getTamanho()-1)
                ,settings.getDimensao(),settings.getDimensao());

        int numeroRelativo = numero-1;
        int k=0;
        int posX=0;
        int posY=0;

        for (int i = 0; i <settings.getTamanho() ; i++) {
            for (int j = 0; j <settings.getTamanho() ; j++) {
                if(numeroRelativo==k){
                    posX=j;
                    posY=i;
                }
                k++;
            }
        }

        return sprite.getSubimage(posX*settings.getDimensao(),posY*settings.getDimensao(),settings.getDimensao(),settings.getDimensao());
    }
    public BufferedImage imagemCortadaRP(int numero){

        if(numero==0) return imgDefault.getSubimage(settings.getDimensao()*(settings.getTamanho()-1),settings.getDimensao()*(settings.getTamanho()-1)
                ,settings.getDimensao(),settings.getDimensao());

        int numeroRelativo = numero-1;
        int k=0;
        int posX=0;
        int posY=0;

        for (int i = 0; i <settings.getTamanho() ; i++) {
            for (int j = 0; j <settings.getTamanho() ; j++) {
                if(numeroRelativo==k){
                    posX=j;
                    posY=i;
                }
                k++;
            }
        }

        return sprite2.getSubimage(posX*settings.getDimensao(),posY*settings.getDimensao(),settings.getDimensao(),settings.getDimensao());
    }
    public int gerarRandom(int max){
        return rdn.nextInt(max);
    }

    public SettingsModel getSettings() {
        return settings;
    }
    public void setSettings(String tipo) {
        settings = SettingsDAO.getSettings(tipo);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Font getTituloFont() {
        return tituloFont;
    }

    public Font getSubTituloFont() {
        return subTituloFont;
    }

    public Font getTextFont() {
        return textFont;
    }

    public ScoreModel getJogador() {
        return jogador;
    }

    public void setSprite() {
        board = boards.get(rdn.nextInt(boards.size()));
        String path = String.format("/img/%s.png",board);

        if(modoJogo.equals("numerico")) board = "Tradicional";

        try {
            if(estado.equals("inicio")) sprite = ImageIO.read(getClass().getResource( "/img/inicio.png"));
            else if(modoJogo.equals("numerico") && estado.equals("jogando")){
                sprite = ImageIO.read(getClass().getResource(settings.getPath()+ "/0.png"));
                sprite2 = ImageIO.read(getClass().getResource(settings.getPath()+ "/1.png"));

            }
            else if(modoJogo.equals("imagens") && estado.equals("jogando")) sprite = ImageIO.read(getClass().getResource(path));
            else if(estado.equals("fim")) sprite = ImageIO.read(getClass().getResource( "/img/fim.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setSprite(String board) {
        this.board = board;
        String path = String.format("/img/%s.png",board);

        try {
            sprite = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void newJogador(){
        jogador = new ScoreModel();
        tempo = new TimeModel();
    }

    public TimeModel getTempo() {
        return tempo;
    }

    public Font getButtonFont() {
        return buttonFont;
    }

    public String getModoJogo() {
        return modoJogo;
    }

    public void setModoJogo(String modoJogo) {
        if(modoJogo.equals("0")) this.modoJogo = "numerico";
        if(modoJogo.equals("1")) this.modoJogo = "imagens";
    }

    public int getJogadas() {
        return jogadas;
    }

    public void setJogadas(int jogadas) {
        this.jogadas = jogadas;
    }
    public void incrementarJogadas(){
        jogadas++;
    }

    public String getBoard() {
        return board;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void tocarSwap(){
        swapPecasClip.setFramePosition(0);
        swapPecasClip.start();
    }

    public void tocarWin(){
        swapPecasClip.setFramePosition(0);
        winClip.start();
    }

    public Image getIcon() {
        return icon;
    }

    public ArrayList<String> getBoards() {
        return boards;
    }

    public void pausar(int segundos){
        try {
            Thread.sleep(segundos*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage[] getMiniaturas() {
        return miniaturas;
    }
}
