1. Algoritmos Avaliados
Os algoritmos de ordenação foram divididos em três grupos para facilitar a comparação:

Grupo A:
Insert Sort
Selection Sort
Comb Sort
Grupo B:
Merge Sort
Quick Sort
Shell Sort
Grupo C:
Radix Sort
Timsort
Stooge Sort
2. Métricas de Avaliação
Para cada execução de um algoritmo, as seguintes métricas são coletadas:

Tempo de Execução: O tempo total (em nanossegundos) que o algoritmo levou para ordenar o vetor.
Número de Trocas: A quantidade de vezes que dois elementos foram trocados de posição no vetor. Para algoritmos que não realizam trocas diretas (como Merge Sort ou Insert Sort via deslocamento), este contador reflete o número de movimentos ou "trocas conceituais".
Número de Iterações: A contagem de passos ou operações fundamentais realizadas pelo algoritmo (ex: comparações, acessos a elementos em loops principais).
3. Metodologia de Teste
A avaliação é realizada seguindo a seguinte metodologia:

Tamanhos de Vetor: Os algoritmos são testados com vetores de inteiros de 1.000, 10.000, 100.000, 500.000 e 1.000.000 elementos.
Dados Aleatórios: Os vetores são preenchidos aleatoriamente.
Múltiplas Rodadas: Para cada combinação de algoritmo e tamanho de vetor, 5 rodadas de execução são realizadas, utilizando diferentes seeds para o gerador de números aleatórios. Isso garante que os resultados representem uma média confiável e minimizam o impacto de variações pontuais.
Replicabilidade: O uso de seeds permite replicar os conjuntos de dados exatos para cada rodada, garantindo a consistência dos testes.
Otimização de Performance: Para evitar tempos de execução excessivamente longos, o Stooge Sort é intencionalmente pulado para vetores com 100.000 elementos ou mais, devido à sua complexidade de tempo de O(N 
2.7
 ).
4. Estrutura do Projeto
Com a estrutura simplificada, seu projeto estará organizado da seguinte forma:

.
├── src/                      # Pasta raiz do seu código-fonte
│   ├── avaliador/            # Pacote principal para a classe de avaliação
│   │   └── AvaliadorOrdenacao.java
│   └── ordenacao/            # Pacote para as classes relacionadas a ordenação
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
Classes Principais:
AvaliadorOrdenacao.java: A classe principal que gerencia o ciclo de testes, gera os dados, executa os algoritmos, coleta as métricas, imprime as médias no console e exporta os resultados brutos.
AlgoritmoOrdenacao.java: Uma classe abstrata que serve como base para todos os algoritmos de ordenação. Ela define a interface comum (ordenar) e gerencia os contadores de trocas e iterações.
ResultadoExecucao.java: Uma classe de modelo de dados que encapsula os resultados de uma única execução de um algoritmo. Também possui um método estático para exportar todos os resultados para um arquivo CSV.
Classes de Algoritmos: Cada arquivo em ordenacao/ (e.g., InsertSort.java, MergeSort.java) contém a implementação específica de um algoritmo de ordenação, herdando de AlgoritmoOrdenacao e registrando suas métricas internas.
5. Como Compilar e Executar
Siga estes passos para compilar e executar o projeto com a estrutura simplificada:

Organize as Pastas: Crie a pasta src e, dentro dela, as pastas avaliador e ordenacao. Mova seus arquivos Java para a pasta correspondente ao seu pacote.
Abra o Terminal/Prompt de Comando: Navegue até o diretório src na sua estrutura de pastas.
Compile o Código: Use o compilador Java (javac) para compilar todas as classes:
Bash

javac avaliador/*.java ordenacao/*.java
Execute a Aplicação: Após a compilação bem-sucedida, execute a classe principal AvaliadorOrdenacao (lembre-se de usar o nome completo do pacote):
Bash

java avaliador.AvaliadorOrdenacao
6. Resultados e Análise
Durante a execução, o programa exibirá o desempenho de cada algoritmo no console.
Ao final da execução, um arquivo chamado resultados_execucao_brutos.csv será gerado no diretório de onde você executou o comando java (neste caso, na pasta src).
Este arquivo CSV contém todos os dados brutos de cada rodada e pode ser importado para ferramentas como Microsoft Excel, Google Sheets, Python (com Pandas, Matplotlib, Seaborn), ou R para uma análise mais detalhada e a criação de gráficos comparativos.
