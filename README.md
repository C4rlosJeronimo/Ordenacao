# Análise de Algoritmos de Ordenação

## 1. Algoritmos Avaliados

Os algoritmos de ordenação foram divididos em três grupos para facilitar a comparação:

- **Grupo A:**
  - Insert Sort
  - Selection Sort
  - Comb Sort

- **Grupo B:**
  - Merge Sort
  - Quick Sort
  - Shell Sort

- **Grupo C:**
  - Radix Sort
  - Timsort
  - Stooge Sort

---

## 2. Métricas de Avaliação

Para cada execução de um algoritmo, foram coletadas as seguintes métricas:

- **Tempo de Execução:** Tempo total (em nanossegundos) necessário para ordenar o vetor.
- **Número de Trocas:** Quantidade de vezes que dois elementos foram trocados de posição. Para algoritmos baseados em deslocamento (como Merge Sort), esse contador representa o número de movimentos.
- **Número de Iterações:** Quantidade de operações fundamentais realizadas pelo algoritmo, como comparações e acessos em loops principais.

---

## 3. Metodologia de Teste

A avaliação foi conduzida conforme os seguintes critérios:

- **Tamanhos dos Vetores:** 1.000, 10.000, 100.000, 500.000 e 1.000.000 elementos.
- **Dados Aleatórios:** Vetores gerados aleatoriamente.
- **Rodadas:** 5 rodadas por combinação de algoritmo e tamanho, utilizando seeds diferentes para garantir replicabilidade e reduzir ruído estatístico.
- **Limite para Stooge Sort:** Para vetores com 100.000 elementos ou mais, o Stooge Sort é automaticamente ignorado devido ao seu custo computacional extremamente alto (complexidade de tempo O(n².7)).

---

## 4. Estrutura do Projeto

```
.
├── src/                         # Pasta raiz do código-fonte
│   ├── avaliador/               # Pacote com a classe de avaliação
│   │   └── AvaliadorOrdenacao.java
│   └── ordenacao/               # Pacote com os algoritmos de ordenação
│       ├── AlgoritmoOrdenacao.java
│       ├── ResultadoExecucao.java
│       ├── CombSort.java
│       ├── InsertSort.java
│       ├── MergeSort.java
│       ├── QuickSort.java
│       ├── RadixSort.java
│       ├── SelectionSort.java
│       ├── ShellSort.java
│       ├── StoogeSort.java
│       └── Timsort.java
```

### Principais Classes

- **AvaliadorOrdenacao.java:** Classe principal que executa os testes, gera dados, executa os algoritmos, coleta as métricas e exporta os resultados para CSV.
- **AlgoritmoOrdenacao.java:** Classe abstrata que define a estrutura base dos algoritmos, incluindo contadores de trocas e iterações.
- **ResultadoExecucao.java:** Modelo que armazena os resultados de cada execução e gera o arquivo CSV.
- **Classes dos Algoritmos:** Cada arquivo em `ordenacao/` implementa um algoritmo específico, herdando de `AlgoritmoOrdenacao`.

---

## 5. Resultados e Análise

- Durante a execução, o desempenho de cada algoritmo será exibido no console.
- Ao final, será gerado um arquivo chamado:

```
resultados_execucao_brutos.csv
```

- Este arquivo estará na pasta onde o comando `java` foi executado (geralmente dentro de `src/`).

### Análise dos Dados

- O CSV contém os dados brutos de todas as rodadas e tamanhos.
- Você pode importar esse arquivo para ferramentas como:
  - Excel
  - Google Sheets
  - Python (com bibliotecas como Pandas, Matplotlib e Seaborn)
  - R
- A partir dos dados, é possível gerar gráficos comparativos e realizar análises de desempenho detalhadas.
