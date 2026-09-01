package ExeLista3;

public class ControleCorrida {

	private boolean safetyCarAtivo;

	private Classificacao classificacao;

	private final Object monitorSafetyCar = new Object();

	public ControleCorrida() {

		safetyCarAtivo = false;
		classificacao = new Classificacao();
	}

	public synchronized void ativarSafetyCar() {
		if (!safetyCarAtivo) {
			safetyCarAtivo = true;
			System.out.println("\n       SAFETY CAR NA PISTA!");
			System.out.println("       Velocidade limitada a 80 km/h\n");
			synchronized (monitorSafetyCar) {
				monitorSafetyCar.notifyAll();
			}
		}
	}

	public synchronized void liberarSafetyCar() {
		if (safetyCarAtivo) {
			safetyCarAtivo = false;
			System.out.println("\n       SAFETY CAR LIBERADO!");
			System.out.println("       Corrida normalizada!\n");
			synchronized (monitorSafetyCar) {
				monitorSafetyCar.notifyAll();
			}
		}
	}

	public void esperarMudancaSafetyCar() throws InterruptedException {
		synchronized (monitorSafetyCar) {
			monitorSafetyCar.wait();
		}
	}

	public synchronized boolean isSafetyCarAtivo() {

		return safetyCarAtivo;
	}

	public void registrarChegada(Carro carro) {

		classificacao.registrarChegada(carro);
	}

	public void registrarAbandono(Carro carro) {

		classificacao.registrarAbandono(carro);
	}

	public Classificacao getClassificacao() {

		return classificacao;
	}
}