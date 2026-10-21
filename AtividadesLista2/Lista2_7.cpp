#include <windows.h>
#include <assert.h>
#include <stdio.h>
#include <omp.h>
#include <stdlib.h>
#include <time.h>

int main (int argc, char *argv[]){
    int n;
    int inicio, fim;   // limites do intervalo, digitados pelo usuário
    int id, num;       // declaradas fora, mas vão ser private na região paralela

    // pede a quantidade de threads pro usuário
    printf("Digite o numero de threads: ");
    scanf("%d", &n);

    // pede o início e o fim do intervalo dos números aleatórios
    printf("Digite o valor de inicio: ");
    scanf("%d", &inicio);
    printf("Digite o valor de fim: ");
    scanf("%d", &fim);

    // verificador de entrada do usuário, não faz sentido rodar com zero ou negativo
    if (n <= 0){
        printf("Valor invalido, o numero de threads precisa ser maior que 0.\n");
        return 1;
    }

    // o inicio não pode ser maior que o fim, senão o intervalo não existe
    if (inicio > fim){
        printf("Valor invalido, o inicio precisa ser menor ou igual ao fim.\n");
        return 1;
    }

    printf("\nSerial - criando %d threads (numeros de %d a %d)...\n\n", n, inicio, fim);

    // inicio e fim shared todas as threads só irão ler, então não tem conflito
    // id e num private cada thread tem a sua cópia
    #pragma omp parallel num_threads(n) shared(inicio, fim) private(id, num)
    {
        id = omp_get_thread_num();   // id da thread (0 até n-1)

        // cada thread começa o rand com uma semente diferente
        // sem o + id, todas gerariam os mesmos números
        srand(time(NULL) + id);

        // cada thread gera 10 numeros
        for (int i = 0; i < 10; i++){
            // rand() % (fim - inicio + 1) dá um valor de 0 até (fim - inicio)
            // somando o inicio, cai dentro do intervalo pedido
            num = inicio + rand() % (fim - inicio + 1);
            printf("Thread id = %d gerou o numero %d \n", id, num);
        }
    }

    // terminou a região paralela, volta pra serial
    printf("\nSerial - fim \n");

    return 0;
}