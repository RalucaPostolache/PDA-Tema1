package prodcons;
import java.util.List;

public class Consumator extends Thread{
	private List<Integer> coada;
	
	public Consumator(List<Integer> coada) {
		super();
		this.coada = coada;
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
		while(true) {
			Main.lock.lock(); 
			while (coada.isEmpty()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int nr = coada.remove(0);
			Main.lock.unlock();
			System.out.println("Numarul " + nr + " este consumat");
			consume(nr);
			}
		}
	}
