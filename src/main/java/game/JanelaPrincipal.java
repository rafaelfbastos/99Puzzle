package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.util.ArrayList;

public class JanelaPrincipal extends JFrame {

    private JPanel painelLateral;
    private Game game;
    private JButton iniciar;
    private JButton reiniciar;
    private JButton score;
    private JButton trocarImg;

    private ArrayList<JRadioButton> radioButtons;
    public JanelaPrincipal() {
        super("Puzzle");
        configRadioButtons();
        game = new Game();
        game.setPreferredSize(new Dimension(740,740));
        game.setBackground(Color.darkGray);
        this.setIconImage(Recursos.getInstance().getIcon());
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setLocation(200,30);
        this.setLayout(new FlowLayout());
        this.add(game);
        configPainelLateral();
        this.add(painelLateral);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);

    }

    public void configPainelLateral(){
        if(painelLateral!=null) painelLateral.removeAll();
        else painelLateral= new JPanel();

        painelLateral.setBackground(Color.black);
        painelLateral.setLayout(new BoxLayout(painelLateral,BoxLayout.Y_AXIS));
        painelLateral.add(Box.createRigidArea(new Dimension(0,20)));
        painelLateral.setPreferredSize(new Dimension(400,740));
        JLabel titulo = new JLabel("nPuzzle");
        titulo.setFont(Recursos.getInstance().getTituloFont());
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0xFFE600));

        painelLateral.add(titulo);

        if(Recursos.getInstance().getEstado().equals("inicio")){
            painelLateral.add(Box.createRigidArea(new Dimension(0,30)));
            JLabel subTitulo =new JLabel("Escolha o tipo de jogo:");
            subTitulo.setAlignmentX(CENTER_ALIGNMENT);
            subTitulo.setFont(Recursos.getInstance().getSubTituloFont());
            subTitulo.setForeground(new Color(0xFFE600));
            painelLateral.add(subTitulo);
            painelLateral.add(Box.createRigidArea(new Dimension(0,30)));
            ButtonGroup escolhaTipo = new ButtonGroup();

            for (JRadioButton radioButton:radioButtons) {
                escolhaTipo.add(radioButton);
                radioButton.setFont(Recursos.getInstance().getTextFont());
                radioButton.setAlignmentX(CENTER_ALIGNMENT);
                radioButton.setBackground(Color.black);
                radioButton.setFocusPainted(false);
                radioButton.setForeground(new Color(0xFFE600));
                painelLateral.add(radioButton);
            }
            painelLateral.add(Box.createRigidArea(new Dimension(0,50)));
            configButtonIniciar();
            painelLateral.add(iniciar);
        }
        if(Recursos.getInstance().getEstado().equals("jogando")){
            titulo.setText(Recursos.getInstance().getJogador().getJogo());
            painelLateral.add(Box.createRigidArea(new Dimension(0,50)));
            JLabel subTitulo =new JLabel(Recursos.getInstance().getJogador().getNome());
            subTitulo.setForeground(new Color(0x545454));
            subTitulo.setAlignmentX(CENTER_ALIGNMENT);
            subTitulo.setFont(Recursos.getInstance().getTituloFont());
            painelLateral.add(subTitulo);
            painelLateral.add(Box.createRigidArea(new Dimension(0,30)));
            JLabel jogadas = new JLabel(""+Recursos.getInstance().getJogadas());
            jogadas.setForeground(new Color(0xFF5757));
            jogadas.setFont(Recursos.getInstance().getTituloFont());
            jogadas.setAlignmentX(CENTER_ALIGNMENT);
            painelLateral.add(jogadas);
            painelLateral.add(Box.createRigidArea(new Dimension(0,50)));
            if(Recursos.getInstance().getModoJogo().equals("imagens")){
                Image image = Recursos.getInstance().getSprite().getScaledInstance(150,150,Image.SCALE_DEFAULT);
                JLabel miniImagem = new JLabel(new ImageIcon(image));
                miniImagem.setAlignmentX(CENTER_ALIGNMENT);
                painelLateral.add(miniImagem);
                painelLateral.add(Box.createRigidArea(new Dimension(0,30)));
                configTrocarImgButton();
                painelLateral.add(trocarImg);

            }
            else {painelLateral.add(Box.createRigidArea(new Dimension(0,150)));}
            painelLateral.add(Box.createRigidArea(new Dimension(0,30)));
            configButtonReiniciar();
            painelLateral.add(reiniciar);
        }
        if(Recursos.getInstance().getEstado().equals("fim")){

            painelLateral.add(Box.createRigidArea(new Dimension(0,50)));
            JLabel subTitulo =new JLabel(Recursos.getInstance().getJogador().getNome());
            subTitulo.setForeground(new Color(0x545454));
            subTitulo.setAlignmentX(CENTER_ALIGNMENT);
            subTitulo.setFont(Recursos.getInstance().getTituloFont());
            painelLateral.add(subTitulo);
            painelLateral.add(Box.createRigidArea(new Dimension(0,30)));
            JLabel jogadas = new JLabel(""+Recursos.getInstance().getJogadas());
            jogadas.setForeground(new Color(0xFF5757));
            jogadas.setFont(Recursos.getInstance().getTituloFont());
            jogadas.setAlignmentX(CENTER_ALIGNMENT);
            painelLateral.add(jogadas);
            painelLateral.add(Box.createRigidArea(new Dimension(0,70)));
            JLabel tempoLabel = new JLabel(Recursos.getInstance().getTempo().toString());
            tempoLabel.setAlignmentX(CENTER_ALIGNMENT);
            tempoLabel.setFont(Recursos.getInstance().getSubTituloFont());
            tempoLabel.setForeground(new Color(0xA6A6A6));
            painelLateral.add(tempoLabel);
            painelLateral.add(Box.createRigidArea(new Dimension(0,50)));
            configButtonReiniciar();
            painelLateral.add(reiniciar);

        }
        painelLateral.add(Box.createRigidArea(new Dimension(0,30)));
        configScoreButton();
        painelLateral.add(score);

    }

    private void configTrocarImgButton() {
        trocarImg = new JButton("Trocar Imagem");
        trocarImg.setAlignmentX(CENTER_ALIGNMENT);
        trocarImg.setFont(Recursos.getInstance().getButtonFont());
        trocarImg.addActionListener(e -> {
            new ImagemJanela();
        });

    }

    public void configRadioButtons(){

        radioButtons = new ArrayList<>();
        radioButtons.add(new JRadioButton("15Puzzle"));
        radioButtons.add(new JRadioButton("24Puzzle"));
        radioButtons.add(new JRadioButton("35Puzzle"));
        radioButtons.add(new JRadioButton("48Puzzle"));
        radioButtons.add(new JRadioButton("63Puzzle"));
        radioButtons.add(new JRadioButton("80Puzzle"));
        radioButtons.add(new JRadioButton("99Puzzle"));

    }

    public void configButtonIniciar(){
        iniciar = new JButton("Iniciar");
        iniciar.setAlignmentX(CENTER_ALIGNMENT);
        iniciar.setFont(Recursos.getInstance().getButtonFont());
        iniciar.addActionListener(e -> {
            for (JRadioButton radioButton: radioButtons  ) {
                if(radioButton.isSelected()) Recursos.getInstance().setSettings(radioButton.getText());
            }
            String nome = JOptionPane.showInputDialog(this, "Informe seu nome:", "Bem vindo!",
                    JOptionPane.PLAIN_MESSAGE);

            Object[] jogoOptions = {"Números","Imagens"};
            Object jogoOption = JOptionPane.showOptionDialog(this,"Como deseja jogar:",
                    "Aviso",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,jogoOptions,jogoOptions[0]);

            if(nome!=null && !nome.isEmpty()&& !jogoOption.toString().equals("-1")){
                Recursos.getInstance().newJogador();
                Recursos.getInstance().getJogador().setNome(nome);
                Recursos.getInstance().getJogador().setJogo(Recursos.getInstance().getSettings().getTipoJogo());
                Recursos.getInstance().getTempo().setTempoInicial(System.currentTimeMillis());
                Recursos.getInstance().setEstado("jogando");
                Recursos.getInstance().setModoJogo((jogoOption.toString()));
                Recursos.getInstance().setSprite();
                game.reiniciar();
                game.render();
                render();
            }
        });

    }

    public void configButtonReiniciar(){
        reiniciar = new  JButton("Reiniciar");
        reiniciar.setAlignmentX(CENTER_ALIGNMENT);
        reiniciar.setFont(Recursos.getInstance().getButtonFont());
        reiniciar.addActionListener(e -> {
            Recursos.getInstance().newJogador();
            Recursos.getInstance().setEstado("inicio");
            Recursos.getInstance().setJogadas(0);
            game.reiniciar();
            game.render();
            render();
        });

    }
    public void configScoreButton(){
        score = new  JButton("Rank");
        score.setAlignmentX(CENTER_ALIGNMENT);
        score.setFont(Recursos.getInstance().getButtonFont());
        score.addActionListener(e -> new ScoreJanela());

    }

    public void render(){
        SwingUtilities.updateComponentTreeUI(this);
        configPainelLateral();
    }

    public void reiniciarImg(String board){
        Recursos.getInstance().getTempo().setTempoInicial(System.currentTimeMillis());
        Recursos.getInstance().setSprite(board);
        Recursos.getInstance().setJogadas(0);
        game.reiniciar();
        game.render();
        render();
    }

}
