package Ordenacao;

public class InsertSort extends AlgoritmoOrdenacao {
    @Override
    public void ordenar(int[] vetor) {
        resetContadores();
        int n = vetor.length;
        for (int i = 1; i < n; ++i) {
            int key = vetor[i];
            int j = i - 1;
            iteracoes++;

            while (j >= 0 && vetor[j] > key) {
                vetor[j + 1] = vetor[j];
                j = j - 1;
                trocas++;
                iteracoes++;
            }
            vetor[j + 1] = key;
        }
    }
}