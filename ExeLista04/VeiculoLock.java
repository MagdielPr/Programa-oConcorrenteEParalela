package ExeLista04;

public class VeiculoLock implements Runnable {

	private int numero;
	private int tempoPermanencia;
	private CancelaLock cancela;

	public VeiculoLock(int numero, int tempoPermanencia, CancelaLock cancela) {
		this.numero = numero;
		this.tempoPermanencia = tempoPermanencia;
		this.cancela = cancela;
	}

	@Override
	public void run() {
		System.out.println("Veículo " + numero + " chegou à fila - Tempo permanência: " + tempoPermanencia + "h");

		cancela.usarCancela(numero, tempoPermanencia);
	}
}