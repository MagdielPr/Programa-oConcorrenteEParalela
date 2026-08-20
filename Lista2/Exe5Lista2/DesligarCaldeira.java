package Exe5Lista2;

public class DesligarCaldeira extends Thread {
	private Caldeira caldeira;

	public DesligarCaldeira(Caldeira caldeira) {
		super("Thread de desligar caldeira");
		this.caldeira = caldeira;
	}

	@Override
	public void run() {
		while (caldeira.estaLigada()) {
			int temperatura = caldeira.getTemperatura();

			if (temperatura > 1600) {
				caldeira.desligar("Temperatura acima do limite de segurança de 1.600 °C.");
				return;
			}

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
	}
}