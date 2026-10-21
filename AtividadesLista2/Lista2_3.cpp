#include <windows.h>
#include <assert.h>
#include <stdio.h>
#include <omp.h>

int main (int argc, char *argv[]){
    int saldo = 50;  // variável original, valor inicial que as threads vão herdar
    printf("Serial - saldo = %d \n", saldo);

    // cada thread cria uma cópia de saldo já começando com 50
    // diferente do private, o valor de fora vem junto pra dentro
    #pragma omp parallel firstprivate(saldo) num_threads(4)
    {
        int id = omp_get_thread_num();   // id da thread (0 a 3)

        // aqui não precisa inicializar a cópia já chegou valendo 50
        // cada thread soma o bônus dela em cima da própria cópia
        saldo = saldo + (id * 100);

        printf("Paralela - Thread id = %d e saldo = %d \n", id, saldo);
    }

    // saiu da região paralela, as cópias foram descartadas
    // o saldo original continua valendo 50
    printf("Serial - saldo = %d \n", saldo);

    return 0;
}