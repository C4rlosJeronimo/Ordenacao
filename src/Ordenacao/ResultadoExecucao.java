package Ordenacao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResultadoExecucao {
    private String nomeAlgoritmo;
    private int tamanhoVetor;
    private long tempoExecucaoNano;
    private long numTrocas;
    private long numIteracoes;
    private int rodada;

    public ResultadoExecucao(String nomeAlgoritmo, int tamanhoVetor, int rodada, long tempoExecucaoNano, long numTrocas, long numIteracoes) {
        this.nomeAlgoritmo = nomeAlgoritmo;
        this.tamanhoVetor = tamanhoVetor;
        this.rodada = rodada;
        this.tempoExecucaoNano = tempoExecucaoNano;
        this.numTrocas = numTrocas;
        this.numIteracoes = numIteracoes;
    }

    public String getNomeAlgoritmo() {
        return nomeAlgoritmo;
    }

    public int getTamanhoVetor() {
        return tamanhoVetor;
    }

    public int getRodada() {
        return rodada;
    }

    public long getTempoExecucaoNano() {
        return tempoExecucaoNano;
    }

    public long getNumTrocas() {
        return numTrocas;
    }

    public long getNumIteracoes() {
        return numIteracoes;
    }

    public static void exportarParaCSV(List<ResultadoExecucao> resultados, String nomeArquivo) {
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.append("Algoritmo,TamanhoVetor,Rodada,TempoExecucaoNano,NumTrocas,NumIteracoes\n");
            for (ResultadoExecucao res : resultados) {
                writer.append(String.format("%s,%d,%d,%d,%d,%d\n",
                        res.getNomeAlgoritmo(),
                        res.getTamanhoVetor(),
                        res.getRodada(),
                        res.getTempoExecucaoNano(),
                        res.getNumTrocas(),
                        res.getNumIteracoes()));
            }
            System.out.println("Dados exportados para " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao exportar dados para CSV: " + e.getMessage());
        }
    }
}
