package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScoreDAO {

    public static ArrayList<ScoreModel> findAll() {
        ArrayList<ScoreModel> scores = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "SELECT * FROM scores";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet resultados = stm.executeQuery();

            while (resultados.next()){
                ScoreModel score = new ScoreModel();
                score.setBoard(resultados.getString("board"));
                score.setJogadas(resultados.getInt("jogadas"));
                score.setJogo(resultados.getString("jogo"));
                score.setNome(resultados.getString("nome"));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(resultados.getString("data"),formatter);
                score.setData(data);

                TimeModel tempo = new TimeModel();
                tempo.setTempo(resultados.getLong("tempo"));
                score.setTempo(tempo);
                scores.add(score);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return scores;
    }

    public static void add(ScoreModel jogador) {

        try (Connection conn = ConnectionFactory.getConnection()) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String data = formatter.format(jogador.getData());

            String sql = "INSERT INTO scores(nome,board,tempo,data,jogo,jogadas) VALUES (?,?,?,?,?,?)";
            PreparedStatement pmt = conn.prepareStatement(sql);
            pmt.setString(1,jogador.getNome());
            pmt.setString(2,jogador.getBoard());
            pmt.setLong(3,jogador.getTempo().getTempo());
            pmt.setString(4,data);
            pmt.setString(5,jogador.getJogo());
            pmt.setInt(6,jogador.getJogadas());

            int rowsAffects = pmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
