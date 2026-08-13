package ListaExercicios;

import java.util.Random;

public class GeradorNumerosImparesPares implements Runnable {
	private boolean ePar;
	private int quantidade;

	public GeradorNumerosImparesPares(boolean ePar, int quantidade) {
		this.ePar = ePar;
		this.quantidade = quantidade;
	}

	@Override
	public void run() {
		Random rand = new Random();
		for (int i = 0; i < quantidade; i++) {
			int numero;
			if (ePar) {
				do {
					numero = rand.nextInt(100);
				} while (numero % 2 != 0);
				System.out.println("Thread Par - Número: " + numero);
			} else {
				do {
					numero = rand.nextInt(100);
				} while (numero % 2 == 0);
				System.out.println("Thread Impar - Número: " + numero);
			}
		}
	}
}