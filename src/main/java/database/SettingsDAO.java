package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SettingsDAO {

    public static SettingsModel getSettings(String tipo){
        SettingsModel settings = null;

        try(Connection conn = ConnectionFactory.getConnection()){

            String sql = "SELECT * FROM settings WHERE jogo = ?";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,tipo);

            ResultSet resultado = stm.executeQuery();

            while (resultado.next()){

                String tipoJogo = resultado.getString("jogo");
                int nPartes = resultado.getInt("n_partes");
                int tamanho = resultado.getInt("tamanho");
                int espacamento = resultado.getInt("espacamento");
                String path = resultado.getString("path");
                int dimensao = resultado.getInt("dimensao");

                settings = new SettingsModel(tipoJogo,nPartes,tamanho,espacamento,path,dimensao);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return settings;

    }


}
