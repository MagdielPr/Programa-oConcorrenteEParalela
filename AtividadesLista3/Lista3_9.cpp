#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <omp.h>

int main(int argc, char *argv[]) {
    const int TAM = 100000;
    int vetor[TAM];
    
    int valorBuscado = 42;
    int posicaoEncontrada = -1; // Variável compartilhada 

    srand(time(NULL));

    // Inicialização sequencial 
    for (int i = 0; i < TAM; i++) {
        vetor[i] = rand() % 1000; // Valores entre 0 e 999
    }

    // Garantimos que o valor exista em uma posição conhecida para o teste
    vetor[50000] = valorBuscado;

    printf("Buscando o valor %d em um vetor de %d elementos...\n\n", valorBuscado, TAM);

    // 1. Paraleliza a busca com #pragma omp for 
    #pragma omp parallel for
    for (int i = 0; i < TAM; i++) {
        if (vetor[i] == valorBuscado) {
            
            // 2. Protege a atualização da variável compartilhada com #pragma omp critical
            #pragma omp critical
            {
                // Checa se ainda é -1 para garantir que pegamos uma ocorrência válida
                // e evitar que outras threads sobrescrevam depois
                if (posicaoEncontrada == -1) {
                    posicaoEncontrada = i;
                }
            }
        }
    }

    // 3. Imprime o resultado final apenas uma vez com #pragma omp single 
    #pragma omp single
    {
        if (posicaoEncontrada != -1) {
            printf("Sucesso! Valor %d encontrado na posicao: %d\n", valorBuscado, posicaoEncontrada);
        } else {
            printf("Valor nao encontrado no vetor.\n");
        }
    }

    return 0;
}