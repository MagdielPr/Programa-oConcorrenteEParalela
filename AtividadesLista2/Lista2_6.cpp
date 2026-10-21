#include <windows.h>
#include <assert.h>
#include <stdio.h>
#include <omp.h>
#include <stdlib.h>
#include <time.h>


int main (int argc, char *argv[]){
    int n;
    int id, num;   // declaradas fora, mas vão ser private na região paralela

    // pede a quantidade de threads pro usuário
    printf("Digite o numero de threads: ");
    scanf("%d", &n);

    // verificador de entrada do usuário pois não faz sentido rodar com zero ou negativo
    if (n <= 0){
        printf("Valor invalido, precisa ser maior que 0.\n");
        return 1;
    }

    printf("\nSerial - criando %d threads...\n\n", n);

    // id e num private cada thread tem a sua cópia
    #pragma omp parallel num_threads(n) private(id, num)
    {
        id = omp_get_thread_num();   // id da thread (0 até n-1)

        // cada thread começa o rand com uma semente diferente
        // sem o + id, todas gerariam os mesmos números
        srand(time(NULL) + id);

        // cada thread gera 10 numeros
        for (int i = 0; i < 10; i++){
            num = rand() % 1001;   // numero de 0 a 1000
            printf("Thread id = %d gerou o numero %d \n", id, num);
        }
    }

    // terminou a região paralela, volta pra serial
    printf("\nSerial - fim \n");

    return 0;
}