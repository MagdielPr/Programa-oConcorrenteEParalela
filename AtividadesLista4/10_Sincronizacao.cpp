#include <stdio.h>
#include <omp.h>

int main() {
    const int TAM = 4;
    int dados[TAM] = {0, 0, 0, 0};

    #pragma omp parallel
    {
        int id = omp_get_thread_num();

        // ETAPA 1
        dados[id] = 10;
        printf("Thread %d concluiu a Etapa 1\n", id);

        // Sincronização
        #pragma omp barrier

        // ETAPA 2
        dados[id] = dados[id] * 2;
        printf("Thread %d concluiu a Etapa 2 (Valor final: %d)\n", id, dados[id]);
    }
    return 0;
}