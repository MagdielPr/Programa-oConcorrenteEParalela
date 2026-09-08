package ExeLista04;

import java.util.ArrayList;
import java.util.List;

public class PrincipalSynchronized {
	public static void main(String[] args) {

		CancelaSynchronized cancela = new CancelaSynchronized();
		List<Thread> veiculos = new ArrayList<>();

		System.out.println("ESTACIONAMENTO - VERSÃO SYNCHRONIZED");

		for (int i = 1; i <= 10; i++) {
			int tempo = (int) (Math.random() * 8) + 1;
			Thread thread = new Thread(new VeiculoSynchronized(i, tempo, cancela));
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
	}
}