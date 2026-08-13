package ListaExercicios;

public class GeradorNumerosImparesParesMain {
	public static void main(String[] args) {
		Thread threadPar = new Thread(new GeradorNumerosImparesPares(true, 3));
		Thread threadImpar = new Thread(new GeradorNumerosImparesPares(false, 4));
		threadPar.start();
		threadImpar.start();
	}
}
