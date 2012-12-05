/**
 * Producer.java
 *
 * This is the producer thread for the bounded buffer problem solved with a pipe.
 *
 */
package samples.ErzVerbrPipes;
import java.io.*;

public class Producer extends Thread {
	private PipedOutputStream pipe;
	private int producedItemCounter = 0;

	public Producer(PipedOutputStream outPipe) {
		pipe = outPipe;
	}

	public void run() {
		byte item;

		while (!isInterrupted()) {
			// Item erzeugen und in den Buffer einstellen
			item = (byte) (Math.random() * 128);
			producedItemCounter++;
			System.err.println("Produced item No. "
					+ producedItemCounter);
			try {
				pipe.write(item);
			} catch (IOException e) {
				interrupt();
			}
			// Der Producer schl�ft nicht (in diesem Beispielcode)
			// --> ist deutlich schneller als der Consumer!
		}
	}

}
