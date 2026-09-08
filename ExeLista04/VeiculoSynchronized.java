package ExeLista04;

public class VeiculoSynchronized implements Runnable {

	private int numero;
	private int tempoPermanencia;
	private CancelaSynchronized cancela;

	public VeiculoSynchronized(int numero, int tempoPermanencia, CancelaSynchronized cancela) {
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