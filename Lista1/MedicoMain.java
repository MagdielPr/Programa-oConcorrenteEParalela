package ListaExercicios;

public class MedicoMain {
	public static void main(String[] args) {
		Thread medico1 = new Thread(new Medico("Dr. Silva"));
		Thread medico2 = new Thread(new Medico("Dr. Oliveira"));
		medico1.start();
		medico2.start();
	}
}
