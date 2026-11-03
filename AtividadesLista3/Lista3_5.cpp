#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <omp.h>

int main(int argc, char *argv[]) {
    const int TAM = 100000;
    int vetor[TAM];
    long long somaTotal = 0;

    srand(time(NULL));

    // Inicializa o vetor
    for (int i = 0; i < TAM; i++) {
        vetor[i] = rand() % 100;
    }

    double inicio = omp_get_wtime();

    // Soma paralela com reduction
    #pragma omp parallel for reduction(+:somaTotal)
    for (int i = 0; i < TAM; i++) {
        somaTotal += vetor[i];
    }

    double fim = omp_get_wtime();

    printf("Soma total do vetor: %lld\n", somaTotal);
    printf("Tempo de execucao: %f segundos\n", fim - inicio);
    printf("Numero de threads: %d\n", omp_get_max_threads());

    return 0;
}