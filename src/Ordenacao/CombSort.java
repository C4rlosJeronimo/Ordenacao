package Ordenacao;

public class CombSort extends AlgoritmoOrdenacao {
    @Override
    public void ordenar(int[] vetor) {
        resetContadores();
        int n = vetor.length;
        int gap = n;
        boolean swapped = true;

        while (gap != 1 || swapped) {
            iteracoes++;

            gap = (int) (gap / 1.3);
            if (gap < 1) {
                gap = 1;
            }

            swapped = false;
            for (int i = 0; i < n - gap; i++) {
                iteracoes++;
                if (vetor[i] > vetor[i + gap]) {
                    int temp = vetor[i];
                    vetor[i] = vetor[i + gap];
                    vetor[i + gap] = temp;
                    swapped = true;
                    trocas++;
                }
            }
        }
    }
}