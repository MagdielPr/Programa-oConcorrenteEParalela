package Exe4Lista2;

import java.util.Random;

public class ContadorBaixa extends Thread {
	private Random random = new Random();

	public ContadorBaixa() {
		super("Contador de baixa prioridade");
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(getName() + ": " + i);

			if (random.nextBoolean()) {
				try {
					int tempoPausa = random.nextInt(1000) + 200;

					System.out.println(getName() + " pausou por " + tempoPausa + " ms.");

					Thread.sleep(tempoPausa);
				} catch (InterruptedException e) {
					System.out.println(getName() + " foi interrompida.");
					Thread.currentThread().interrupt();
					return;
				}
			}
		}

		System.out.println(getName() + " finalizou.");
	}
}