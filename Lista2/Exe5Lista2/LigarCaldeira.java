package Exe5Lista2;

public class LigarCaldeira extends Thread {
	private Caldeira caldeira;

	public LigarCaldeira(Caldeira caldeira) {
		super("Thread de ligar caldeira");
		this.caldeira = caldeira;
	}

	@Override
	public void run() {
		caldeira.ligar();
	}
}