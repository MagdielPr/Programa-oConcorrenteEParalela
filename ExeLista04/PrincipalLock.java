package ExeLista04;

import java.util.ArrayList;
import java.util.List;

public class PrincipalLock {
	public static void main(String[] args) {

		CancelaLock cancela = new CancelaLock();
		List<Thread> veiculos = new ArrayList<>();

		System.out.println("ESTACIONAMENTO - VERSÃO LOCK");

		for (int i = 1; i <= 10; i++) {
			int tempo = (int) (Math.random() * 8) + 1;
			Thread thread = new Thread(new VeiculoLock(i, tempo, cancela));
			veiculos.add(thread);
		}

		for (Thread t : veiculos) {
			t.start();
		}

		for (Thread t : veiculos) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("RESULTADO FINAL");
		System.out.println("Total arrecadado: R$ " + cancela.getTotalArrecadado());
		System.out.println("Total de veículos: " + cancela.getOrdemSaida());

		// EXPLICAÇÃO SOBRE A VARIAÇÃO DA ORDEM: A ordem de atendimento pode variar
		// entre
		// as execuções porque o escalonador de threads do Java decide qual thread
		// executa primeiro, as threads são iniciadas quase simultaneamente e quando
		// múltiplas threads tentam adquirir o lock/synchronized não há garantia de
		// ordem FIFO (primeiro a chegar, primeiro a ser atendido) e depende do sistema
		// operacional e da implementação da JVM

	}
}