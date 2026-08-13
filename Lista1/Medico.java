package ListaExercicios;

import java.util.Random;

class Medico implements Runnable {
	private String nome;
	private Random rand;

	public Medico(String nome) {
		this.nome = nome;
		this.rand = new Random();
	}

	@Override
	public void run() {
		System.out.println(nome + " começou a atender pacientes.");
		for (int i = 0; i < 5; i++) {
			int tempoAtendimento = rand.nextInt(1000) + 1000;
			System.out.println(nome + " atendeu paciente " + (i + 1) + " por " + tempoAtendimento + "ms");
			try {
				Thread.sleep(tempoAtendimento);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(nome + " terminou o atendimento.");
	}
}