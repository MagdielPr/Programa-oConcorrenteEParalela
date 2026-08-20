package Exe5Lista2;

public class Caldeira {
	private boolean ligada = false;
	private int temperatura = 25;

	public synchronized boolean estaLigada() {
		return ligada;
	}

	public synchronized int getTemperatura() {
		return temperatura;
	}

	public synchronized void ligar() {
		if (!ligada) {
			ligada = true;
			temperatura = 25;
			System.out.println("Caldeira ligada. Temperatura inicial: " + temperatura + " °C.");
		} else {
			System.out.println("A caldeira já está ligada.");
		}
	}

	public synchronized void alimentar(int aumentoTemperatura) {
		if (ligada) {
			temperatura += aumentoTemperatura;

			System.out.println("Caldeira alimentada. Aumento: " + aumentoTemperatura + " °C. Temperatura atual: "
					+ temperatura + " °C.");
		}
	}

	public synchronized void desligar(String motivo) {
		if (ligada) {
			ligada = false;

			System.out.println("CALDEIRA DESLIGADA!");
			System.out.println("Motivo: " + motivo);
			System.out.println("Temperatura no desligamento: " + temperatura + " °C.");
		}
	}

	public synchronized void limpar() {
		if (!ligada) {
			System.out.println("Limpeza da caldeira iniciada.");
			System.out.println("Limpeza da caldeira concluída.");
		} else {
			System.out.println("Não é possível limpar: a caldeira está ligada.");
		}
	}
}