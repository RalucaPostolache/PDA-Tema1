package prodcons;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class Main {

	public static final int MAX_SIZE = 10;
	static final Lock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> coada = new ArrayList<Integer>();
		
		int max=10;
		
		Semaphore semProducator = new Semaphore(max);
		Semaphore semConsumator = new Semaphore(0);
		Producator producator = new Producator(coada,max, semConsumator, semProducator);
		Consumator consumator = new Consumator(coada, semConsumator, semProducator);
		
		producator.start();
		consumator.start();
		
		try {
		producator.join();
		consumator.join();
		} catch (Exception exception){
            System.out.println(exception);
		}
		
	}

}
