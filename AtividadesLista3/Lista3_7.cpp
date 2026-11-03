#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <omp.h>

int main(int argc, char *argv[]) {
    int vetorA[1000];
    int vetorB[50]; 
    
    long long soma = 0;
    long long produto = 1;

    srand(time(NULL));

    // Inicializa o vetor A (valores de 1 a 10)
    for (int i = 0; i < 1000; i++) {
        vetorA[i] = (rand() % 10) + 1; 
    }
    
    // Inicializa o vetor B (valores 1 ou 2, para o produto não estourar o limite do número)
    for (int i = 0; i < 50; i++) {
        vetorB[i] = (rand() % 2) + 1;  
    }

    printf("Iniciando processamento paralelo com sections...\n\n");

    #pragma omp parallel
    {
        #pragma omp sections
        {
            // Seção 1: calcula a soma do vetor A
            #pragma omp section
            {
                printf("Thread %d calculando a SOMA.\n", omp_get_thread_num());
                for (int i = 0; i < 1000; i++) {
                    soma += vetorA[i];
                }
            }

            // Seção 2: calcula o produto do vetor B
            #pragma omp section
            {
                printf("Thread %d calculando o PRODUTO.\n", omp_get_thread_num());
                for (int i = 0; i < 50; i++) {
                    produto = produto * vetorB[i];
                }
            }
        } 
    }
    printf("\nResultados:\n");
    printf("Soma total do vetor A: %lld\n", soma);
    printf("Produto total do vetor B: %lld\n", produto);

    return 0;
}