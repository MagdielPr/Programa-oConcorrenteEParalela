package Exe5Lista2;

public class LimparCaldeira extends Thread {
	private Caldeira caldeira;

	public LimparCaldeira(Caldeira caldeira) {
		super("Thread de limpar caldeira");
		this.caldeira = caldeira;
	}

	@Override
	public void run() {
		while (caldeira.estaLigada()) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}

		caldeira.limpar();
	}
}