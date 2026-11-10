#include <stdio.h>
#include <omp.h>

int main() {
    int dado = 0;
    int pronto = 0;

    #pragma omp parallel
    {
        int id = omp_get_thread_num();

        if (id == 0) {
            // Thread produtora
            dado = 42;
            #pragma omp flush
            pronto = 1;
            #pragma omp flush
        } else {
            // Threads consumidoras esperam o sinal
            while (pronto == 0) {
                #pragma omp flush(pronto)
            }
            #pragma omp flush(dado)
            printf("Thread %d leu o dado: %d\n", id, dado);
        }
    }
    return 0;
}