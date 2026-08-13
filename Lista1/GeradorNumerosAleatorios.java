package ListaExercicios;

import java.util.Random;

public class GeradorNumerosAleatorios implements Runnable {
	private int quantidade;
	private int min;
	private int max;

	public GeradorNumerosAleatorios(int quantidade, int min, int max) {
		this.quantidade = quantidade; // se refere a variável X mencionada no enunciado da questão
		this.min = min; // se refere a variável A mencionada no enunciado da questão
		this.max = max; // se refere a variável B mencionada no enunciado da questão
	}

	@Override
	public void run() {
		Random rand = new Random();
		for (int i = 0; i < quantidade; i++) {
			int numero = rand.nextInt(max - min + 1) + min;
			System.out.println("Número aleatório: " + numero);
		}
	}
}