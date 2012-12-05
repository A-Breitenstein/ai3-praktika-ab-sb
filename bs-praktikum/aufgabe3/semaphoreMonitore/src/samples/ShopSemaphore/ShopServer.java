/**
 * ShopServer.java
 */
package samples.ShopSemaphore;
public class ShopServer {
	public static void main(String args[]) {
		Shop server = new Shop();

		// Customer - Threads erzeugen
		Customer customer0Thread = new Customer(server);
		Customer customer1Thread = new Customer(server);
		Customer customer2Thread = new Customer(server);
		Customer customer3Thread = new Customer(server);
		Customer customer4Thread = new Customer(server);
		Customer customer5Thread = new Customer(server);

		customer0Thread.start();
		customer1Thread.start();
		customer2Thread.start();
		customer3Thread.start();
		customer4Thread.start();
		customer5Thread.start();

		// Laufzeit abwarten
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}

		customer0Thread.interrupt();
		customer1Thread.interrupt();
		customer2Thread.interrupt();
		customer3Thread.interrupt();
		customer4Thread.interrupt();
		customer5Thread.interrupt();

		System.err.println("-------------------- THE END -------------------");
	}

}
