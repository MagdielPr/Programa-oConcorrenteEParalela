package ListaExercicios;

import java.util.Random;

public class GeradorDePlacas implements Runnable {
	private int quantidade;
	private int min;
	private int max;

	public GeradorDePlacas(int quantidade) {
		this.quantidade = quantidade;
	}

	public void run() {
		Random rand = new Random();
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for (int i = 0; i < quantidade; i++) {

			// Gera as 3 primeiras letras
			char l1 = letras.charAt(rand.nextInt(26));
			char l2 = letras.charAt(rand.nextInt(26));
			char l3 = letras.charAt(rand.nextInt(26));

			// Gera o primeiro número (0 a 9)
			int n1 = rand.nextInt(10);

			// Gera a quarta letra
			char l4 = letras.charAt(rand.nextInt(26));

			// Gera os 2 últimos números (0 a 9)
			int n2 = rand.nextInt(10);
			int n3 = rand.nextInt(10);

			// Junta tudo no formato do Mercosul
			String placa = "" + l1 + l2 + l3 + n1 + l4 + n2 + n3;

			System.out.println("Placa Mercosul: " + placa);

		}
	}
}
