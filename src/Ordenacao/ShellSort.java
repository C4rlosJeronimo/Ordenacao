package Ordenacao;

public class ShellSort extends AlgoritmoOrdenacao {
    @Override
    public void ordenar(int[] vetor) {
        resetContadores();
        int n = vetor.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            iteracoes++;
            for (int i = gap; i < n; i++) {
                iteracoes++;
                int temp = vetor[i];
                int j;
                for (j = i; j >= gap && vetor[j - gap] > temp; j -= gap) {
                    vetor[j] = vetor[j - gap];
                    trocas++;
                    iteracoes++;
                }
                vetor[j] = temp;
                if (j != i) {
                }
            }
        }
    }
}
