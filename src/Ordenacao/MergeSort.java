package Ordenacao;

public class MergeSort extends AlgoritmoOrdenacao {
    @Override
    public void ordenar(int[] vetor) {
        resetContadores();
        mergeSort(vetor, 0, vetor.length - 1);
    }

    private void mergeSort(int[] vetor, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(vetor, left, mid);
            mergeSort(vetor, mid + 1, right);
            merge(vetor, left, mid, right);
        }
    }

    private void merge(int[] vetor, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = vetor[left + i];
            iteracoes++;
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = vetor[mid + 1 + j];
            iteracoes++;
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            iteracoes++;
            if (L[i] <= R[j]) {
                vetor[k] = L[i];
                i++;
            } else {
                vetor[k] = R[j];
                j++;
                trocas++;
            }
            k++;
        }

        while (i < n1) {
            vetor[k] = L[i];
            i++;
            k++;
            iteracoes++;
            trocas++;
        }

        while (j < n2) {
            vetor[k] = R[j];
            j++;
            k++;
            iteracoes++;
            trocas++;
        }
    }
}
