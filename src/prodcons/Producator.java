package prodcons;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Producator extends Thread{

	List<Integer> coada;
	int max;
	Random random = new Random();
	Semaphore semConsumator;
	Semaphore semProducator;
	
	public Producator(List<Integer> coada, int max, Semaphore semConsumator, Semaphore semProducator) {
		super();
		this.coada = coada;
		this.max=max;
		this.semConsumator = semConsumator;
		this.semProducator = semProducator;
	}
	int produce() {
		int nr = random.nextInt(max);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nr;
	}
	
	@Override
	public void run() {
		while(true) {
			int nr=produce();
			Main.lock.lock();
			try {
				semProducator.acquire();
				coada.add(nr);
				semConsumator.release();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			Main.lock.unlock();
			System.out.println("Numarul " + nr + " este produs");
			
		}
	}
	
}
