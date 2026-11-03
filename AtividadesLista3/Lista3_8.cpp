#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <omp.h>

int main(int argc, char *argv[]) {
    const int N = 50;
    int A[N][N];
    int B[N][N];
    int C[N][N];

    srand(time(NULL));

    // Inicializa as matrizes A e B com valores aleatórios e zera a C
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            A[i][j] = rand() % 10; // Valores de 0 a 9
            B[i][j] = rand() % 10;
            C[i][j] = 0;
        }
    }

    printf("Iniciando multiplicacao de matrizes %dx%d com sections...\n\n", N, N);

    #pragma omp parallel
    {
        #pragma omp sections
        {
            // Seção 1: responsável por calcular a primeira metade das linhas
            #pragma omp section
            {
                printf("Thread %d processando a primeira metade das linhas (0 a %d).\n", 
                       omp_get_thread_num(), (N/2)-1);
                
                for (int i = 0; i < N / 2; i++) {
                    for (int j = 0; j < N; j++) {
                        for (int k = 0; k < N; k++) {
                            C[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }

            // Seção 2: responsável por calcular a segunda metade das linhas
            #pragma omp section
            {
                printf("Thread %d processando a segunda metade das linhas (%d a %d).\n", 
                       omp_get_thread_num(), N/2, N-1);
                
                for (int i = N / 2; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        for (int k = 0; k < N; k++) {
                            C[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }
        } 
    } 
    
    // Imprime a matriz C inteira
    printf("\nMatriz Resultado C (%dx%d):\n", N, N);
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            printf("%d\t", C[i][j]);
        }
        printf("\n");
    }

    return 0;
}