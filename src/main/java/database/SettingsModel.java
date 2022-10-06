package database;

public class SettingsModel {

    private String tipoJogo;
    private int nPartes;
    private int tamanho;
    private int espacamento;
    private String path;
    private int dimensao;

    public SettingsModel(String tipoJogo, int nPartes, int tamanho, int espacamento, String path, int dimensao) {
        this.tipoJogo = tipoJogo;
        this.nPartes = nPartes;
        this.tamanho = tamanho;
        this.espacamento = espacamento;
        this.path = path;
        this.dimensao = dimensao;
    }

    public String getTipoJogo() {
        return tipoJogo;
    }

    public void setTipoJogo(String tipoJogo) {
        this.tipoJogo = tipoJogo;
    }

    public int getnPartes() {
        return nPartes;
    }

    public void setnPartes(int nPartes) {
        this.nPartes = nPartes;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getEspacamento() {
        return espacamento;
    }

    public void setEspacamento(int espacamento) {
        this.espacamento = espacamento;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }
}
