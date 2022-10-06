package game;

import database.ScoreDAO;
import database.ScoreModel;
import game.TableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreJanela extends JFrame {

    public ScoreJanela(){
        super("Rank");
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800,600));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(300,150);
        this.setIconImage(Recursos.getInstance().getIcon());
        ArrayList<ScoreModel> scores = ScoreDAO.findAll();
        Collections.sort(scores);
        TableModel tabelaModel = new TableModel(scores);
        JTable tabela = new JTable(tabelaModel);
        this.add(new JScrollPane(tabela));
        this.pack();
        this.setVisible(true);

    }

}
