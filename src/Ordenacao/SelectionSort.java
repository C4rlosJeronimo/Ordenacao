package Ordenacao;

public class SelectionSort extends AlgoritmoOrdenacao {
    @Override
    public void ordenar(int[] vetor) {
        resetContadores();
        int n = vetor.length;

        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            iteracoes++;

            for (int j = i + 1; j < n; j++) {
                iteracoes++;
                if (vetor[j] < vetor[min_idx]) {
                    min_idx = j;
                }
            }

            if (min_idx != i) {
                int temp = vetor[min_idx];
                vetor[min_idx] = vetor[i];
                vetor[i] = temp;
                trocas++;
            }
        }
    }
}