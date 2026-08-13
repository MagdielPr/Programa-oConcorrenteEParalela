package ListaExercicios;

class Carro implements Runnable {
	@Override
	public void run() {
		System.out.println("Foi iniciada a construção do Carro");
		Thread motor = new Thread(new Motor());
		Thread chassi = new Thread(new Chassi());
		Thread pneus = new Thread(new Pneus());
		motor.start();
		chassi.start();
		pneus.start();
		try {
			motor.join();
			chassi.join();
			pneus.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A construção do carro foi finalizada");
	}
}