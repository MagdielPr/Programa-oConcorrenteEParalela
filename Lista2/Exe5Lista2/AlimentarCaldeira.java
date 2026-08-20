package Exe5Lista2;

import java.util.Random;

public class AlimentarCaldeira extends Thread {
	private Caldeira caldeira;
	private Random random = new Random();

	public AlimentarCaldeira(Caldeira caldeira) {
		super("Thread de alimentar caldeira");
		this.caldeira = caldeira;
	}

	@Override
	public void run() {
		while (caldeira.estaLigada()) {
			int aumentoTemperatura = random.nextInt(401) + 100;

			caldeira.alimentar(aumentoTemperatura);

			try {
				Thread.sleep(random.nextInt(1000) + 500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}

		System.out.println("Thread de alimentação encerrada.");
	}
}