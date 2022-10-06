package database;

import java.time.LocalDate;

public class ScoreModel implements Comparable<ScoreModel>{

    private String nome;
    private String board;
    private TimeModel tempo;
    private LocalDate data;
    private int jogadas;
    private String jogo;


    public int getJogadas() {
        return jogadas;
    }

    public void setJogadas(int jogadas) {
        this.jogadas = jogadas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public TimeModel getTempo() {
        return tempo;
    }

    public void setTempo(TimeModel tempo) {
        this.tempo = tempo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getJogo() {
        return jogo;
    }

    public void setJogo(String jogo) {
        this.jogo = jogo;
    }

    @Override
    public int compareTo(ScoreModel o) {
        int compareJogadas = Integer.compare(this.jogadas,o.jogadas);
        return (compareJogadas!=0)? compareJogadas: this.tempo.compareTo(o.tempo);
    }
}
