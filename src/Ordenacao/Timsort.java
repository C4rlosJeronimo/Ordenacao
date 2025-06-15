package Ordenacao;

import java.util.Arrays;

public class Timsort extends AlgoritmoOrdenacao {
    private static final int RUN = 32;

    @Override
    public void ordenar(int[] vetor) {
        resetContadores();
        int n = vetor.length;

        for (int i = 0; i < n; i += RUN) {
            insertionSort(vetor, i, Math.min((i + RUN - 1), (n - 1)));
        }

        for (int size = RUN; size < n; size = 2 * size) {
            iteracoes++;
            for (int left = 0; left < n; left += 2 * size) {
                iteracoes++;
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if (mid < right) {
                    merge(vetor, left, mid, right);
                }
            }
        }
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            iteracoes++;

            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
                trocas++;
                iteracoes++;
            }
            arr[j + 1] = temp;
        }
    }

    private void merge(int[] vetor, int l, int m, int r) {
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];

        for (int x = 0; x < len1; x++) {
            left[x] = vetor[l + x];
            iteracoes++;
        }
        for (int x = 0; x < len2; x++) {
            right[x] = vetor[m + 1 + x];
            iteracoes++;
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < len1 && j < len2) {
            iteracoes++;
            if (left[i] <= right[j]) {
                vetor[k] = left[i];
                i++;
            } else {
                vetor[k] = right[j];
                j++;
                trocas++;
            }
            k++;
        }

        while (i < len1) {
            vetor[k] = left[i];
            k++;
            i++;
            iteracoes++;
            trocas++;
        }

        while (j < len2) {
            vetor[k] = right[j];
            k++;
            j++;
            iteracoes++;
            trocas++;
        }
    }
}