/**
 * BoundedBuffer.java
 */
package samples.ErzVerbrMonitore;
import java.util.*;

public class BoundedBuffer {
	private static final int BUFFER_SIZE = 2;
	private LinkedList<Object> buffer;

	public BoundedBuffer() {
		buffer = new LinkedList<Object>();
	}

	// Producer und Consumer benutzen diese Methode, um sich schlafen zu legen
	public static void sleeping() {
		int sleepTime = (int) (5000 * Math.random());

		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	// Producer ruft die Methode ENTER auf
	public synchronized void enter(Object item) {
		// Falls Puffer voll ==> Warten!
		while (buffer.size() == BUFFER_SIZE) {
			System.err.println("PPPPPPPPPPPPPP Producer "
					+ Thread.currentThread().getName() + " is waiting!");
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
		// Item zum Buffer hinzuf�gen
		buffer.add(item);
		if (buffer.size() == BUFFER_SIZE)
			System.err.println("PPPPPPPPPPPPPP Producer "
					+ Thread.currentThread().getName()
					+ " Entered Item ----------- Buffer FULL");
		else
			System.err.println("PPPPPPPPPPPPPP Producer "
					+ Thread.currentThread().getName()
					+ " Entered Item +++++++++++ Buffer Size = "
					+ buffer.size());

		// Buffer entsperren und ggf.wartenden Producer/Consumer wecken
		notify();
	}

	// Consumer ruft die Methode REMOVE auf
	public synchronized Object remove() {
		Object item;

		// Falls Puffer leer ==> Warten!
		while (buffer.size() == 0) {
			System.err.println("CCCCCCCCCCCCCC Consumer "
					+ Thread.currentThread().getName() + " is waiting!");
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return null;
			}
		}
		// Item aus dem Buffer entfernen
		item = buffer.removeFirst();

		if (buffer.size() == 0)
			System.err.println("CCCCCCCCCCCCCC Consumer "
					+ Thread.currentThread().getName()
					+ " Consumed! ---------- Buffer EMPTY");
		else
			System.err.println("CCCCCCCCCCCCCC Consumer "
					+ Thread.currentThread().getName()
					+ " Consumed! ++++++++++ Buffer Size = " + buffer.size());

		// Buffer entsperren und ggf.wartenden Producer/Consumer wecken
		notify();

		return item;
	}

}
