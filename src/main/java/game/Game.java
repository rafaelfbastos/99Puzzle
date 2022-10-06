package game;

import database.ScoreDAO;
import database.SettingsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;


public class Game extends JPanel {

    private Tabuleiro tabuleiro;


    public Game() {
        tabuleiro = new Tabuleiro();

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                update(mouseX, mouseY);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    public void update(int mouseX, int mouseY) {
        int pecaClicada = tabuleiro.clickedPosition(mouseX, mouseY);
        tabuleiro.swapPecas(pecaClicada);

        if (tabuleiro.verificarVitoria() && Recursos.getInstance().getEstado().equals("jogando")) {
            Recursos.getInstance().getTempo().setTempoFinal(System.currentTimeMillis());
            Recursos.getInstance().getTempo().setTempo();
            Recursos.getInstance().getJogador().setTempo(Recursos.getInstance().getTempo());
            Recursos.getInstance().getJogador().setJogadas(Recursos.getInstance().getJogadas());
            Recursos.getInstance().getJogador().setData(LocalDate.now());
            Recursos.getInstance().getJogador().setBoard(Recursos.getInstance().getBoard());
            ScoreDAO.add(Recursos.getInstance().getJogador());
            Recursos.getInstance().tocarWin();
            Recursos.getInstance().setEstado("fim");
        }
        Main.janela.render();
        render();

    }

    public void reiniciar() {
        tabuleiro.remontarTabuleiro();
    }

    public void render() {
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        super.paintComponent(g2d);


        if (Recursos.getInstance().getEstado().equals("jogando") && Recursos.getInstance().getModoJogo().equals("numerico")) {
            int k = 0;
            SettingsModel settings = Recursos.getInstance().getSettings();
            for (int i = settings.getEspacamento(); i < settings.getTamanho() * settings.getDimensao(); i = i + settings.getDimensao() + settings.getEspacamento()) {
                for (int j = settings.getEspacamento(); j < settings.getTamanho() * settings.getDimensao(); j = j + settings.getDimensao() + settings.getEspacamento()) {
                    int numero = tabuleiro.getTabuleiro().get(k);
                    if (tabuleiro.getTabuleiro().get(k) == tabuleiro.getOrdenado().get(k)) {
                        g2d.drawImage(Recursos.getInstance().imagemCortadaRP(numero), j, i, null);
                    }else{
                        g2d.drawImage(Recursos.getInstance().imagemCortada(numero), j, i, null);
                    }
                    k++;
                }
            }
        }
        else if (Recursos.getInstance().getEstado().equals("jogando")) {
            int k = 0;
            SettingsModel settings = Recursos.getInstance().getSettings();
            for (int i = settings.getEspacamento(); i < settings.getTamanho() * settings.getDimensao(); i = i + settings.getDimensao() + settings.getEspacamento()) {
                for (int j = settings.getEspacamento(); j < settings.getTamanho() * settings.getDimensao(); j = j + settings.getDimensao() + settings.getEspacamento()) {
                    int numero = tabuleiro.getTabuleiro().get(k);
                    if (tabuleiro.getTabuleiro().get(k) == tabuleiro.getOrdenado().get(k) && tabuleiro.getTabuleiro().get(k)!= 0 ) {
                        g2d.setColor(new Color(0xFF5757));
                        g2d.fillRect(j-settings.getEspacamento(),i- settings.getEspacamento(),settings.getDimensao()+(settings.getEspacamento()),
                                settings.getDimensao()+(settings.getEspacamento()));
                    }
                    g2d.drawImage(Recursos.getInstance().imagemCortada(numero), j, i, null);
                    k++;
                }
            }
        }

        if (Recursos.getInstance().getEstado().equals("inicio") || Recursos.getInstance().getEstado().equals("fim")) {
            Recursos.getInstance().setSprite();
            g2d.drawImage(Recursos.getInstance().getSprite(), 0, 0, null);
        }

    }


}
