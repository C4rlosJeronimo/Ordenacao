package Ordenacao;

import java.util.Arrays;

public class RadixSort extends AlgoritmoOrdenacao {
    @Override
    public void ordenar(int[] vetor) {
        resetContadores();
        int m = getMax(vetor);

        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(vetor, exp);
        }
    }

    private int getMax(int[] vetor) {
        int max = vetor[0];
        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i] > max) {
                max = vetor[i];
            }
            iteracoes++;
        }
        return max;
    }

    private void countSort(int[] vetor, int exp) {
        int n = vetor.length;
        int[] output = new int[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[(vetor[i] / exp) % 10]++;
            iteracoes++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            iteracoes++;
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(vetor[i] / exp) % 10] - 1] = vetor[i];
            count[(vetor[i] / exp) % 10]--;
            iteracoes++;
            trocas++;
        }

        for (int i = 0; i < n; i++) {
            vetor[i] = output[i];
            iteracoes++;
        }
    }
}
