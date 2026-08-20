package Exe4Lista2;

public class MainContadores {
	public static void main(String[] args) {
		ContadorAlta contadorAlta = new ContadorAlta();
		ContadorBaixa contadorBaixa = new ContadorBaixa();

		contadorAlta.setPriority(Thread.MAX_PRIORITY);
		contadorBaixa.setPriority(Thread.MIN_PRIORITY);

		contadorAlta.start();
		contadorBaixa.start();

		try {
			contadorAlta.join();
			contadorBaixa.join();

			System.out.println("Os dois contadores foram finalizados.");
			System.out.println("Encerrando o método main.");
		} catch (InterruptedException e) {
			System.out.println("A thread principal foi interrompida.");
			Thread.currentThread().interrupt();
		}
	}
}
