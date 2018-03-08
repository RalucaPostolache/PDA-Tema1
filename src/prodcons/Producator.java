package prodcons;
import java.util.List;
import java.util.Random;

public class Producator extends Thread{

	List<Integer> coada;
	int max;
	Random random = new Random();
	
	public Producator(List<Integer> coada, int max) {
		super();
		this.coada = coada;
		this.max=max;
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
			while(coada.size() == Main.MAX_SIZE) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			coada.add(nr);
			Main.lock.unlock();
			System.out.println("Numarul " + nr + " este produs");
			
		}
	}
	
}
