package Ordenacao;

public class QuickSort extends AlgoritmoOrdenacao {
    @Override
    public void ordenar(int[] vetor) {
        resetContadores();
        quickSort(vetor, 0, vetor.length - 1);
    }

    private void quickSort(int[] vetor, int low, int high) {
        if (low < high) {
            int pi = partition(vetor, low, high);
            quickSort(vetor, low, pi - 1);
            quickSort(vetor, pi + 1, high);
        }
    }

    private int partition(int[] vetor, int low, int high) {
        int pivot = vetor[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            iteracoes++;
            if (vetor[j] < pivot) {
                i++;
                int temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
                trocas++;
            }
        }

        int temp = vetor[i + 1];
        vetor[i + 1] = vetor[high];
        vetor[high] = temp;
        trocas++;

        return i + 1;
    }
}
