package database;

import java.util.concurrent.TimeUnit;

public class TimeModel implements Comparable<TimeModel> {
    private long tempoInicial;
    private long TempoFinal;
    private long tempo;


    public void setTempoInicial(long tempoInicial) {
        this.tempoInicial = tempoInicial;
    }


    public void setTempoFinal(long tempoFinal) {
        TempoFinal = tempoFinal;
    }

    public long getTempo() {
        return tempo;
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }

    public void setTempo(){
        tempo=TempoFinal-tempoInicial;
    }

    @Override
    public int compareTo(TimeModel o) {
        return Long.compare(this.tempo,o.tempo);
    }

    @Override
    public String toString() {

        return String.format("%02d m : %02d s", TimeUnit.MILLISECONDS.toMinutes(tempo),TimeUnit.MILLISECONDS.toSeconds(tempo)%60);
    }
}
