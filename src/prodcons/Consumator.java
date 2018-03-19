package prodcons;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Consumator extends Thread{
	private List<Integer> coada;
	Semaphore semConsumator;
	Semaphore semProducator;
	
	public Consumator(List<Integer> coada, Semaphore semConsumator, Semaphore semProducator) {
		super();
		this.coada = coada;
		this.semConsumator = semConsumator;
		this.semProducator = semProducator;
	}
	
	void consume(int nr) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		int nr=0;
		while(true) {
			Main.lock.lock(); 
			try {
				semConsumator.acquire();
				nr = coada.remove(0);
				semProducator.release();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Main.lock.unlock();
			System.out.println("Numarul " + nr + " este consumat");
			consume(nr);
			}
		}
	}
