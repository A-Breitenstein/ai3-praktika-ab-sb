/**
 * Customer.java
 *
 * This is the customer thread.
 *
 */
package samples.ShopSemaphore;
public class Customer extends Thread {
	private Shop currentShop;

	public Customer(Shop s) {
		currentShop = s;
	}

	public void run() {

		while (!isInterrupted()) {

			// Versuche, in das Gesch�ft einzutreten
			System.err.println("Customer " + this.getName()
					+ " wants to enter the shop!");
			currentShop.enter();

			if (isInterrupted()) {
				break;
			}

			// F�r unbestimmte Zeit schlafen
			enjoyLife();
		}
	}

	// Customer benutzen diese Methode, um sich zu vergn�gen
	public void enjoyLife() {
		int sleepTime = (int) (1000 * Math.random());

		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
