#include <windows.h>
#include <assert.h>
#include <stdio.h>
#include <omp.h>

int main (int argc, char *argv[]){
    int soma = 10;  // valor inicial na thread principal
    printf("Serial - soma = %d \n", soma);

    // cada thread ganha uma cópia privada de soma começando em 0 
    // no final o OpenMP soma todas as cópias e junta com o 10 que estava fora
    #pragma omp parallel reduction(+:soma) num_threads(4)
    {
        int id = omp_get_thread_num();   // id da thread (0 a 3)

        // cada thread soma só na cópia dela, sem conflito
        soma = soma + (id + 1);

        printf("Paralela - Thread id = %d e soma (cópia) = %d \n", id, soma);
    }

    // saiu da região paralela, as cópias foram combinadas
    // 10 + 1 + 2 + 3 + 4 = 20
    printf("Serial - soma = %d \n", soma);

    return 0;
}