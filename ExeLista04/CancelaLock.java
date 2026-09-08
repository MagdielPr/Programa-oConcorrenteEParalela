package ExeLista04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CancelaLock {

	private int totalArrecadado = 0;
	private int ordemSaida = 0;
	private Lock lock = new ReentrantLock();

	public void usarCancela(int numeroVeiculo, int tempoPermanencia) {

		lock.lock();

		try {
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

		} finally {
			lock.unlock();
		}
	}

	public int getTotalArrecadado() {
		lock.lock();
		try {
			return totalArrecadado;
		} finally {
			lock.unlock();
		}
	}

	public int getOrdemSaida() {
		lock.lock();
		try {
			return ordemSaida;
		} finally {
			lock.unlock();
		}
	}
}