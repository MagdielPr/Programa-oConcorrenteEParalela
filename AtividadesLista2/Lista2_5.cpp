#include <windows.h>
#include <assert.h>
#include <stdio.h>
#include <omp.h>

int main (int argc, char *argv[]){
    int n = 1000;  // tamanho do trabalho
    printf("Serial - n = %d \n", n);

    // só paraleliza se n for maior que 500
    // se a condição der falso, o bloco roda só na thread principal
    #pragma omp parallel if(n > 500) num_threads(4)
    {
        int id = omp_get_thread_num();    // id da thread
        int nt = omp_get_num_threads();   // quantas threads estão rodando o bloco

        printf("Thread %d de %d threads (n = %d) \n", id, nt, n);
    }

    // terminou o bloco, de volta na execução serial
    printf("Serial - fim \n");

    return 0;
}