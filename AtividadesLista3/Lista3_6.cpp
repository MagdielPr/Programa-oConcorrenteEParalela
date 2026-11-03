#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <omp.h>

int main(int argc, char *argv[]) {
    int matriz[10][10];

    #pragma omp parallel
    {
        #pragma omp single
        {
            srand(time(NULL));
            printf("Thread %d inicializou o gerador de numeros.\n", omp_get_thread_num());
        }

        // Preenche a matriz em paralelo (distribui as linhas entre as threads)
        #pragma omp for
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matriz[i][j] = rand() % 100; // Números de 0 a 99
            }
        }
    } // Fim da região paralela

    //Imprime o resultado (fora do parallel, executado sequencialmente)
    printf("\nMatriz 10x10 preenchida:\n");
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            printf("%3d ", matriz[i][j]);
        }
        printf("\n");
    }

    return 0;
}