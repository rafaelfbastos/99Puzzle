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
        JPanel panelImg = new JPanel(new GridLayout(0, 3));
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
        for (int i=0;i<boards.size(); i++) {
            String board = boards.get(i);
            JRadioButton button = new JRadioButton(new ImageIcon(Recursos.getInstance().getMiniaturas()[i].getScaledInstance(200,200,Image.SCALE_DEFAULT)));
            button.addActionListener(e -> {
                Main.janela.reiniciarImg(board);
                dispose();
            });
            radioButtons.add(button);
        }
    }

}


