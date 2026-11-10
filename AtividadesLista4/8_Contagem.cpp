#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int main() {
    const int TAM = 1000;
    int vetor[TAM];
    int pares = 0;
    int impares = 0;

    for (int i = 0; i < TAM; i++) {
        vetor[i] = rand() % 100;
    }

    #pragma omp parallel for
    for (int i = 0; i < TAM; i++) {
        if (vetor[i] % 2 == 0) {
            #pragma omp atomic
            pares++;
        } else {
            #pragma omp atomic
            impares++;
        }
    }

    printf("Pares: %d | Impares: %d\n", pares, impares);
    return 0;
}