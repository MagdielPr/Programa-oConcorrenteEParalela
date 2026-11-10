#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <omp.h>

int main() {
    const int PONTOS = 10000; 
    int dentro_do_circulo = 0;

    #pragma omp parallel
    {
        // Semente diferente para cada thread
        srand(time(NULL) + omp_get_thread_num());

        #pragma omp for
        for (int i = 0; i < PONTOS; i++) {
            double x = (double)rand() / RAND_MAX;
            double y = (double)rand() / RAND_MAX;

            if (x * x + y * y <= 1.0) {
                #pragma omp atomic
                dentro_do_circulo++;
            }
        }

        // Garante que todas as threads terminaram de contar
        #pragma omp barrier

        // Apenas uma thread faz o cálculo e imprime
        #pragma omp single
        {
            double pi = 4.0 * (double)dentro_do_circulo / PONTOS;
            printf("Valor estimado de PI: %f\n", pi);
        }
    }
    return 0;
}