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
			while(coada.isEmpty()) {
				try {
					coada.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
			}
			nr = coada.remove(0);
			Main.lock.unlock();
			coada.notifyAll();
			
			
			System.out.println("Numarul " + nr + " este consumat");
			consume(nr);
			}
		}
	}
