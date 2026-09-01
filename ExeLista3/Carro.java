package ExeLista3;

import java.util.Random;

public class Carro extends Thread {

	private int numero;
	private int velocidade;

	private long tempoInicial;
	private long tempoFinal;

	private boolean terminou;
	private String motivoAbandono;

	private ControleCorrida controle;
	private boolean jaFoiAoBox;

	public Carro(int numero, ControleCorrida controle) {

		this.numero = numero;
		this.controle = controle;

		this.terminou = false;
		this.motivoAbandono = "";
		this.jaFoiAoBox = false;
	}

	@Override
	public void run() {

		Random random = new Random();

		tempoInicial = System.currentTimeMillis();

		System.out.println("Carro " + numero + " iniciou a corrida!");

		for (int volta = 1; volta <= 10; volta++) {

			while (controle.isSafetyCarAtivo()) {
				velocidade = random.nextInt(21) + 60;
				System.out.println("Carro " + numero + " - Volta " + volta + " - Velocidade limitada (Safety Car): "
						+ velocidade + " km/h");
				try {
					controle.esperarMudancaSafetyCar();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
			}

			velocidade = random.nextInt(101) + 200;
			System.out.println("Carro " + numero + " - Volta " + volta + " - Velocidade: " + velocidade + " km/h");

			int chanceBox = random.nextInt(100);
			boolean deveEntrarNoBox = false;

			if (volta <= 5) {
				// Voltas 1 a 5: 10% de chance normal
				if (chanceBox < 10) {
					deveEntrarNoBox = true;
				}
			} else {
				// Volta 6 em diante: se ainda nao foi, ENTRA OBRIGATORIAMENTE
				if (!jaFoiAoBox) {
					deveEntrarNoBox = true;
					System.out.println("Carro " + numero + " - Volta " + volta
							+ " - Obrigatorio entrar no box, ainda nao passou!");
				} else {
					// Se ja foi, pode entrar de novo com 10% de chance
					if (chanceBox < 10) {
						deveEntrarNoBox = true;
					}
				}
			}

			if (deveEntrarNoBox) {
				jaFoiAoBox = true;
				System.out.println("Carro " + numero + " entrando no BOX na volta " + volta + "...");

				int velocidadeBox = random.nextInt(41) + 60;
				System.out.println("Carro " + numero + " - Velocidade no box: " + velocidadeBox + " km/h");

				if (velocidadeBox > 80) {
					System.out.println("Carro " + numero + " EXCEDEU o limite no box! Penalidade aplicada!");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Carro " + numero + " passou no box dentro do limite.");
				}

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("Carro " + numero + " saindo do box!");
			}

			try {
				Thread.sleep(random.nextInt(501) + 500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int problema = random.nextInt(100);

			if (problema < 5) {

				motivoAbandono = "Problema tecnico";
				System.out.println("Carro " + numero + " abandonou a corrida! Motivo: " + motivoAbandono);

				controle.ativarSafetyCar();

				new Thread(() -> {
					try {
						Thread.sleep(3000);
						controle.liberarSafetyCar();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}).start();

				finalizarTempo();
				controle.registrarAbandono(this);

				return;
			}
		}

		terminou = true;

		finalizarTempo();

		System.out.println("Carro " + numero + " terminou a corrida!");

		controle.registrarChegada(this);
	}

	private void finalizarTempo() {
		tempoFinal = System.currentTimeMillis();
	}

	public int getNumero() {
		return numero;
	}

	public long getTempoTotal() {
		return tempoFinal - tempoInicial;
	}

	public boolean isTerminou() {
		return terminou;
	}

	public String getMotivoAbandono() {
		return motivoAbandono;
	}
}