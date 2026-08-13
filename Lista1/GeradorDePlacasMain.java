package ListaExercicios;

public class GeradorDePlacasMain {

	public static void main(String[] args) {
		Thread thread = new Thread(new GeradorDePlacas(1));
		thread.start();
	}

}
