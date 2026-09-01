package ExeLista3;

public class MainCorrida {
	public static void main(String[] args) {
		ControleCorrida estadoCorrida = new ControleCorrida();

		Carro carro1 = new Carro(1, estadoCorrida);
		Carro carro2 = new Carro(2, estadoCorrida);
		Carro carro3 = new Carro(3, estadoCorrida);
		Carro carro4 = new Carro(4, estadoCorrida);
		Carro carro5 = new Carro(5, estadoCorrida);

		carro1.setPriority(Thread.NORM_PRIORITY);
		carro2.setPriority(Thread.NORM_PRIORITY);
		carro3.setPriority(Thread.NORM_PRIORITY);
		carro4.setPriority(Thread.NORM_PRIORITY);
		carro5.setPriority(Thread.NORM_PRIORITY);

		System.out.println("Começa a partida:");

		carro1.start();
		carro2.start();
		carro3.start();
		carro4.start();
		carro5.start();

		int carrosNaPista = 5;

		while (carrosNaPista > 0) {
			carrosNaPista = 0;

			if (carro1.isAlive())
				carrosNaPista++;
			if (carro2.isAlive())
				carrosNaPista++;
			if (carro3.isAlive())
				carrosNaPista++;
			if (carro4.isAlive())
				carrosNaPista++;
			if (carro5.isAlive())
				carrosNaPista++;

			if (carrosNaPista > 0) {
				System.out.println("Ainda existem " + carrosNaPista + " carros na pista...");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					System.out.println("Monitoramento interrompido.");
					Thread.currentThread().interrupt();
				}
			}
		}

		System.out.println("Resultado Final:");
		estadoCorrida.getClassificacao().mostrarResultado();

	}
}