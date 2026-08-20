package Exe5Lista2;

public class Main {
	public static void main(String[] args) {
		Caldeira caldeira = new Caldeira();

		LigarCaldeira ligar = new LigarCaldeira(caldeira);
		AlimentarCaldeira alimentar = new AlimentarCaldeira(caldeira);
		DesligarCaldeira desligar = new DesligarCaldeira(caldeira);
		LimparCaldeira limpar = new LimparCaldeira(caldeira);

		ligar.setPriority(Thread.MIN_PRIORITY);
		alimentar.setPriority(Thread.MIN_PRIORITY);
		limpar.setPriority(Thread.MIN_PRIORITY);
		desligar.setPriority(Thread.MAX_PRIORITY);

		ligar.start();

		try {
			ligar.join();

			alimentar.start();
			desligar.start();
			limpar.start();

			alimentar.join();
			desligar.join();
			limpar.join();

			System.out.println("Sistema de controle da caldeira encerrado.");
		} catch (InterruptedException e) {
			System.out.println("A thread principal foi interrompida.");
			Thread.currentThread().interrupt();
		}
	}
}