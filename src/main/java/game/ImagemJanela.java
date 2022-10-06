package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ImagemJanela extends JFrame {
    ArrayList<JRadioButton> radioButtons;

    public ImagemJanela() {
        super("Escolha a imagem");
        setPreferredSize(new Dimension(700, 600));
        setLocation(300, 100);
        this.setIconImage(Recursos.getInstance().getIcon());
        JPanel panelImg = new JPanel(new GridLayout(0, 2));
        this.setLayout(new BorderLayout());
        configButtons();
        for (JRadioButton radioButton : radioButtons) {
            panelImg.add(radioButton);
        }
        JPanel panelTop = new JPanel(new FlowLayout());
        panelTop.setPreferredSize(new Dimension(700, 50));
        JLabel titulo = new JLabel("Escolha o tabuleiro que deseja jogar:");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setFont(Recursos.getInstance().getSubTituloFont());
        panelTop.add(titulo);
        ScrollPane scr = new ScrollPane();
        scr.add(panelImg);
        add(scr, BorderLayout.CENTER);
        add(panelTop, BorderLayout.NORTH);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    public void configButtons() {
        radioButtons = new ArrayList<>();
        ArrayList<String> boards = new ArrayList<>(Recursos.getInstance().getBoards());
        boards.forEach((board)->{
            JRadioButton rb = new JRadioButton(board);
            rb.setFont(Recursos.getInstance().getSubTituloFont());
            rb.addActionListener(e -> {
                Recursos.getInstance().setSprite(board);
                Main.janela.render();
                dispose();
            });
            radioButtons.add(rb);
        });
    }

}


