package ListaExercicios;

public class MainGeradorNumerosAleatorios {

	public static void main(String[] args) {
		Thread thread = new Thread(new GeradorNumerosAleatorios(5, 1, 5));
		thread.start();
	}

}
