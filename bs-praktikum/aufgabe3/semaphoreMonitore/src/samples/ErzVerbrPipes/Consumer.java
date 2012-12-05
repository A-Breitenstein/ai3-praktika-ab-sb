/**
 * Consumer.java
 *
 * This is the consumer thread for the bounded buffer problem solved with a pipe
 *
 */
package samples.ErzVerbrPipes;
import java.io.*;

public class Consumer extends Thread {
	private PipedInputStream pipe;
	private int consumedItemCounter = 0;

	public Consumer(PipedInputStream inPipe) {
		pipe = inPipe;
	}

	public void run() {
		byte item;

		while (!isInterrupted()) {

			// Item aus dem Buffer verbrauchen
			try {
				item = (byte) pipe.read();
				consumedItemCounter++;
				System.err.println("------------------- Consumed Item No. "
						+ consumedItemCounter);
			} catch (IOException e) {
				interrupt();
			}
			// F�r unbestimmte Zeit schlafen
			sleeping();
		}
	}

	private void sleeping() {
		int sleepTime = (int) (100 * Math.random());

		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			interrupt();
		}
	}

}
