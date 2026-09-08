package ExeLista04;

public class CancelaSynchronized {

	private int totalArrecadado = 0;
	private int ordemSaida = 0;

	public synchronized void usarCancela(int numeroVeiculo, int tempoPermanencia) {

		double valorPagamento = tempoPermanencia * 2.5;

		System.out.println("Veículo " + numeroVeiculo + " entrou na cancela");
		System.out.println("Veículo " + numeroVeiculo + " efetuando pagamento: R$ " + valorPagamento);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ordemSaida++;
		totalArrecadado += valorPagamento;

		System.out.println("Veículo " + numeroVeiculo + " saiu - Ordem: " + ordemSaida);
		System.out.println();
	}

	public synchronized int getTotalArrecadado() {
		return totalArrecadado;
	}

	public synchronized int getOrdemSaida() {
		return ordemSaida;
	}
}