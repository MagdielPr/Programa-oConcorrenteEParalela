#include <windows.h>
#include <assert.h>
#include <stdio.h>
#include <omp.h>

int main (int argc, char *argv[]){
    int vetor[4] = {0, 0, 0, 0};  // vetor compartilhado, todas as threads enxergam o mesmo
    int bonus = 5;                // também compartilhada, só leitura aqui

    printf("Serial - vetor[0] = %d \n", vetor[0]);

    // vetor e bonus são shared então não tem cópia, é tudo a mesma memória
    // 4 threads, uma pra cada posição do vetor
    #pragma omp parallel shared(vetor, bonus) num_threads(4)
    {
        int id = omp_get_thread_num();   // id da thread (0 a 3)

        // cada thread escreve só na posição dela então não tem conflito
        // todas leem o mesmo bonus, que é o mesmo pra todo mundo
        vetor[id] = (id + 1) * bonus;

        printf("Paralela - Thread id = %d escreveu vetor[%d] = %d \n", id, id, vetor[id]);
    }

    // saiu da região paralela e o que as threads escreveram continua aqui
    // por ser shared, a thread principal consegue ver tudo
    printf("Serial - vetor = [%d, %d, %d, %d] \n", vetor[0], vetor[1], vetor[2], vetor[3]);

    return 0;
}