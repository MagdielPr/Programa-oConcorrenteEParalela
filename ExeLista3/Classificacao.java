package ExeLista3;

import java.util.ArrayList;
import java.util.List;

public class Classificacao {

	private List<Carro> classificados;
	private List<Carro> abandonos;

	public Classificacao() {

		classificados = new ArrayList<>();
		abandonos = new ArrayList<>();
	}

	public synchronized void registrarChegada(Carro carro) {

		classificados.add(carro);
	}

	public synchronized void registrarAbandono(Carro carro) {

		abandonos.add(carro);
	}

	public void mostrarResultado() {

		System.out.println();
		System.out.println("          CLASSIFICACAO FINAL");

		for (int i = 0; i < classificados.size(); i++) {

			Carro carro = classificados.get(i);

			System.out.println((i + 1) + "o - Carro " + carro.getNumero() + " - Tempo: "
					+ String.format("%.2f", carro.getTempoTotal() / 1000.0) + " segundos");
		}

		System.out.println();
		System.out.println("             ABANDONOS");

		if (abandonos.isEmpty()) {

			System.out.println("Nenhum carro abandonou a corrida.");

		} else {

			for (Carro carro : abandonos) {

				System.out.println("Carro " + carro.getNumero() + " - Tempo: "
						+ String.format("%.2f", carro.getTempoTotal() / 1000.0) + " segundos" + " - Motivo: "
						+ carro.getMotivoAbandono());
			}
		}
	}

	public List<Carro> getClassificados() {

		return classificados;
	}

	public List<Carro> getAbandonos() {

		return abandonos;
	}
}