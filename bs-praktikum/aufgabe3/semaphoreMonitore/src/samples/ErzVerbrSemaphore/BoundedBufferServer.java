/**
 * BoundedBufferServer.java
 */
package samples.ErzVerbrSemaphore;
public class BoundedBufferServer {
	public static void main(String args[]) {
		BoundedBuffer server = new BoundedBuffer();

		// Producer und Consumer - Threads erzeugen
		Producer producer1Thread = new Producer(server);
		Consumer consumer1Thread = new Consumer(server);

		Producer producer2Thread = new Producer(server);
		Consumer consumer2Thread = new Consumer(server);

		producer1Thread.start();
		consumer1Thread.start();

		producer2Thread.start();
		consumer2Thread.start();

		// Laufzeit abwarten
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}

		producer1Thread.interrupt();
		consumer1Thread.interrupt();
		producer2Thread.interrupt();
		consumer2Thread.interrupt();

		System.err.println("-------------------- THE END -------------------");
	}

}
