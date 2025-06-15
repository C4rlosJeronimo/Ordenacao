package Ordenacao;

public abstract class AlgoritmoOrdenacao {
    protected long trocas;
    protected long iteracoes;

    public void resetContadores() {
        this.trocas = 0;
        this.iteracoes = 0;
    }

    public long getTrocas() {
        return trocas;
    }

    public long getIteracoes() {
        return iteracoes;
    }

    public abstract void ordenar(int[] vetor);
}