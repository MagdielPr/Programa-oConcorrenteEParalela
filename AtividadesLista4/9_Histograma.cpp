#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int main() {
    const int TAM = 1000;
    int vetor[TAM];
    int hist[10] = {0}; // Histograma de 0 a 9, iniciado com zeros

    // Preenche o vetor com números de 0 a 9
    for (int i = 0; i < TAM; i++) {
        vetor[i] = rand() % 10;
    }

    // Conta as ocorrências em paralelo
    #pragma omp parallel for
    for (int i = 0; i < TAM; i++) {
        #pragma omp critical
        {
            hist[vetor[i]]++; // Proteção segura contra race condition
        }
    }

    printf("Resultado do Histograma:\n");
    for (int i = 0; i < 10; i++) {
        printf("Valor %d: %d vezes\n", i, hist[i]);
    }
    return 0;
}