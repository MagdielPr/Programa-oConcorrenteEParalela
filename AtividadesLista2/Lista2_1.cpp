#include <windows.h>
#include <assert.h>
#include <stdio.h>
#include <omp.h>

int main (int argc, char *argv[]){
    int total = 100;  // variável original, fica na thread principal
    printf("Serial - total = %d \n", total);

    // cada thread cria uma cópia própria de total
    #pragma omp parallel private(total) num_threads(4)
    {
        // a cópia privada começa sem valor por isso zero aqui dentro
        // o 100 de fora não vem pra ca
        total = 0;

        int id = omp_get_thread_num();   // pega o id da thread

        // cada thread soma no total dela, sem atrapalhar as outras
        total = total + (id * 10);

        printf("Paralela - Thread id = %d e total = %d \n", id, total);
    }

    // saiu da região paralela, as cópias sumiram
    // o total original continua valendo 100
    printf("Serial - total = %d \n", total);

    return 0;
}