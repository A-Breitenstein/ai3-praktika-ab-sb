/**
 * BoundedBuffer.java with locks and conditions
 */
package samples.ErzVerbrConditions;
import java.util.*;
import java.util.concurrent.locks.*;

public class BoundedBuffer {
	private static final int BUFFER_SIZE = 2;
	private LinkedList<Object> buffer;

	final Lock bufferLock = new ReentrantLock();
	final Condition notFull = bufferLock.newCondition();
	final Condition notEmpty = bufferLock.newCondition();

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
	public void enter(Object item) {
		// Zugriff auf Buffer sperren
		bufferLock.lock();

		// Falls Puffer voll ==> Warten!
		while (buffer.size() == BUFFER_SIZE) {
			System.err.println("PPPPPPPPPPPPPP Producer "
					+ Thread.currentThread().getName() + " is waiting!");
			try {
				notFull.await(); // Warte auf Bedingung "not full"
								// --> eigene Warteschlange!
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				// Zugriff auf Buffer freigeben
				bufferLock.unlock();
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

		// Buffer entsperren und ggf. wartenden Consumer wecken (spezielle
		// Warteschlange!)
		notEmpty.signal();

		// Zugriff auf Buffer freigeben
		bufferLock.unlock();
	}

	// Consumer ruft die Methode REMOVE auf
	public Object remove() {
		Object item;

		// Zugriff auf Buffer sperren
		bufferLock.lock();

		// Falls Puffer leer ==> Warten!
		while (buffer.size() == 0) {
			System.err.println("CCCCCCCCCCCCCC Consumer "
					+ Thread.currentThread().getName() + " is waiting!");
			try {
				notEmpty.await(); // Warte auf Bedingung "not empty"
									// --> eigene Warteschlange!
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				// Zugriff auf Buffer freigeben
				bufferLock.unlock();
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

		// Buffer entsperren und ggf.wartenden Producer wecken
		notFull.signal();

		// Zugriff auf Buffer freigeben
		bufferLock.unlock();

		return item;
	}

}
