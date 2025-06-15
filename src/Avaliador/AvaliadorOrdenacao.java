package Avaliador;

import Ordenacao.AlgoritmoOrdenacao;
import Ordenacao.ResultadoExecucao;
import Ordenacao.CombSort;
import Ordenacao.InsertSort;
import Ordenacao.MergeSort;
import Ordenacao.QuickSort;
import Ordenacao.RadixSort;
import Ordenacao.SelectionSort;
import Ordenacao.ShellSort;
import Ordenacao.StoogeSort;
import Ordenacao.Timsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Map;

public class AvaliadorOrdenacao {

    public static void main(String[] args) {
        int[] tamanhosVetor = {1000, 10000, 100000, 500000, 1000000};
        int numRodadas = 5;

        List<AlgoritmoOrdenacao> algoritmos = new ArrayList<>();
        // Grupo A
        algoritmos.add(new InsertSort());
        algoritmos.add(new SelectionSort());
        algoritmos.add(new CombSort());
        // Grupo B
        algoritmos.add(new MergeSort());
        algoritmos.add(new QuickSort());
        algoritmos.add(new ShellSort());
        // Grupo C
        algoritmos.add(new RadixSort());
        algoritmos.add(new Timsort());
        algoritmos.add(new StoogeSort());

        List<ResultadoExecucao> todosResultados = new ArrayList<>();

        System.out.println("Iniciando avaliação dos algoritmos de ordenação...");

        for (int tamanho : tamanhosVetor) {
            System.out.println("\n--- Tamanho do Vetor: " + tamanho + " ---");
            for (int rodada = 0; rodada < numRodadas; rodada++) {
                int seed = rodada + 1;
                Random rand = new Random(seed);
                int[] vetorOriginal = new int[tamanho];
                for (int i = 0; i < tamanho; i++) {
                    vetorOriginal[i] = rand.nextInt(tamanho * 10);
                }

                System.out.println("  Rodada " + (rodada + 1) + " (Seed: " + seed + ")");

                for (AlgoritmoOrdenacao algoritmo : algoritmos) {
                    if (algoritmo instanceof StoogeSort && tamanho >= 100000) {
                        System.out.println("    Skipping StoogeSort for size " + tamanho + " due to extreme slowness.");
                        continue;
                    }

                    int[] vetorParaOrdenar = Arrays.copyOf(vetorOriginal, vetorOriginal.length);
                    algoritmo.resetContadores();

                    long inicio = System.nanoTime();
                    try {
                        algoritmo.ordenar(vetorParaOrdenar);
                    } catch (StackOverflowError e) {
                        System.err.println("    ERRO: StackOverflowError para " + algoritmo.getClass().getSimpleName() + " com tamanho " + tamanho);
                        continue;
                    }
                    long fim = System.nanoTime();

                    todosResultados.add(new ResultadoExecucao(
                            algoritmo.getClass().getSimpleName(),
                            tamanho,
                            rodada + 1,
                            fim - inicio,
                            algoritmo.getTrocas(),
                            algoritmo.getIteracoes()
                    ));
                    System.out.printf("    - %s: Tempo %.3f ms, Trocas %d, Iterações %d%n",
                            algoritmo.getClass().getSimpleName(),
                            (fim - inicio) / 1_000_000.0,
                            algoritmo.getTrocas(),
                            algoritmo.getIteracoes());
                }
            }
        }

        ResultadoExecucao.exportarParaCSV(todosResultados, "resultados_execucao_brutos.csv");

        System.out.println("\n--- Médias dos Resultados ---");

        Map<String, Map<Integer, List<ResultadoExecucao>>> agrupadosPorAlgoritmoETamanho =
                todosResultados.stream().collect(Collectors.groupingBy(ResultadoExecucao::getNomeAlgoritmo,
                        Collectors.groupingBy(ResultadoExecucao::getTamanhoVetor)));

        System.out.println("\n--- Média de Tempo de Execução (ms) ---");
        System.out.printf("%-18s", "Algoritmo");
        for (int tamanho : tamanhosVetor) {
            boolean tamanhoFoiUsado = todosResultados.stream()
                    .anyMatch(r -> r.getTamanhoVetor() == tamanho);
            if (tamanhoFoiUsado) {
                System.out.printf("%12d", tamanho);
            }
        }
        System.out.println();
        imprimirLinhaSeparadora(tamanhosVetor.length);

        algoritmos.stream()
                .map(AlgoritmoOrdenacao::getClass)
                .map(Class::getSimpleName)
                .sorted()
                .forEach(algName -> {
                    System.out.printf("%-18s", algName);
                    for (int tamanho : tamanhosVetor) {
                        Map<Integer, List<ResultadoExecucao>> resultadosPorTamanho = agrupadosPorAlgoritmoETamanho.get(algName);
                        if (resultadosPorTamanho != null && resultadosPorTamanho.containsKey(tamanho)) {
                            double avgTime = resultadosPorTamanho.get(tamanho).stream()
                                    .mapToLong(ResultadoExecucao::getTempoExecucaoNano)
                                    .average()
                                    .orElse(0.0) / 1_000_000.0;
                            System.out.printf("%12.3f", avgTime);
                        } else {
                            System.out.printf("%12s", "N/A");
                        }
                    }
                    System.out.println();
                });

        System.out.println("\n--- Média de Número de Trocas ---");
        System.out.printf("%-18s", "Algoritmo");
        for (int tamanho : tamanhosVetor) {
            boolean tamanhoFoiUsado = todosResultados.stream()
                    .anyMatch(r -> r.getTamanhoVetor() == tamanho);
            if (tamanhoFoiUsado) {
                System.out.printf("%12d", tamanho);
            }
        }
        System.out.println();
        imprimirLinhaSeparadora(tamanhosVetor.length);

        algoritmos.stream()
                .map(AlgoritmoOrdenacao::getClass)
                .map(Class::getSimpleName)
                .sorted()
                .forEach(algName -> {
                    System.out.printf("%-18s", algName);
                    for (int tamanho : tamanhosVetor) {
                        Map<Integer, List<ResultadoExecucao>> resultadosPorTamanho = agrupadosPorAlgoritmoETamanho.get(algName);
                        if (resultadosPorTamanho != null && resultadosPorTamanho.containsKey(tamanho)) {
                            double avgTrocas = resultadosPorTamanho.get(tamanho).stream()
                                    .mapToLong(ResultadoExecucao::getNumTrocas)
                                    .average()
                                    .orElse(0.0);
                            System.out.printf("%12.0f", avgTrocas);
                        } else {
                            System.out.printf("%12s", "N/A");
                        }
                    }
                    System.out.println();
                });

        System.out.println("\n--- Média de Número de Iterações ---");
        System.out.printf("%-18s", "Algoritmo");
        for (int tamanho : tamanhosVetor) {
            boolean tamanhoFoiUsado = todosResultados.stream()
                    .anyMatch(r -> r.getTamanhoVetor() == tamanho);
            if (tamanhoFoiUsado) {
                System.out.printf("%12d", tamanho);
            }
        }
        System.out.println();
        imprimirLinhaSeparadora(tamanhosVetor.length);

        algoritmos.stream()
                .map(AlgoritmoOrdenacao::getClass)
                .map(Class::getSimpleName)
                .sorted()
                .forEach(algName -> {
                    System.out.printf("%-18s", algName);
                    for (int tamanho : tamanhosVetor) {
                        Map<Integer, List<ResultadoExecucao>> resultadosPorTamanho = agrupadosPorAlgoritmoETamanho.get(algName);
                        if (resultadosPorTamanho != null && resultadosPorTamanho.containsKey(tamanho)) {
                            double avgIteracoes = resultadosPorTamanho.get(tamanho).stream()
                                    .mapToLong(ResultadoExecucao::getNumIteracoes)
                                    .average()
                                    .orElse(0.0);
                            System.out.printf("%12.0f", avgIteracoes);
                        } else {
                            System.out.printf("%12s", "N/A");
                        }
                    }
                    System.out.println();
                });

        System.out.println("\n--- Avaliação Concluída ---");
        System.out.println("Os resultados brutos foram exportados para 'resultados_execucao_brutos.csv'.");
        System.out.println("Você pode usar este arquivo para gerar gráficos em ferramentas como Excel, Google Sheets, ou Python com bibliotecas como Matplotlib/Seaborn.");
    }

    private static void imprimirLinhaSeparadora(int numColunas) {
        System.out.print("------------------");
        for (int i = 0; i < numColunas; i++) {
            System.out.print("------------");
        }
        System.out.println();
    }
}
