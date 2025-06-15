package Ordenacao;

public class StoogeSort extends AlgoritmoOrdenacao {
    @Override
    public void ordenar(int[] vetor) {
        resetContadores();
        stoogeSort(vetor, 0, vetor.length - 1);
    }

    private void stoogeSort(int[] arr, int l, int h) {
        iteracoes++;
        if (l >= h) {
            return;
        }

        if (arr[l] > arr[h]) {
            int temp = arr[l];
            arr[l] = arr[h];
            arr[h] = temp;
            trocas++;
        }

        if (h - l + 1 > 2) {
            int t = (h - l + 1) / 3;

            stoogeSort(arr, l, h - t);

            stoogeSort(arr, l + t, h);

            stoogeSort(arr, l, h - t);
        }
    }
}
