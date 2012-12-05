/**
 * BoundedBuffer.java
 */
package samples.ErzVerbrSemaphore;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class BoundedBuffer {
	private static final int BUFFER_SIZE = 2;

	private ReentrantLock mutex_S; // = S: Synchronisation des Zugriffs
	private Semaphore F; // = F: Anzahl freier Pl�tze
	private Semaphore B; // = B: Anzahl belegter Pl�tze

	private LinkedList<Object> buffer;

	public BoundedBuffer() {
		buffer = new LinkedList<Object>();

		mutex_S = new ReentrantLock();
		F = new Semaphore(BUFFER_SIZE);
		B = new Semaphore(0);
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
		try {
			// Versuche, die Anzahl freier Pl�tze zu erniedrigen. Falls auf Null
			// ==> Warten!
			F.acquire();
			// Buffer f�r Zugriff gesperrt?
			mutex_S.lockInterruptibly();
		} catch (InterruptedException e) {
			// Erneutes Setzen des Interrupt-Flags
			Thread.currentThread().interrupt();
		}

		if (Thread.currentThread().isInterrupted()) {
			return;
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
		mutex_S.unlock();
		// Anzahl belegter Pl�tze erh�hen und ggf.wartenden Consumer wecken
		B.release();
	}

	// Consumer ruft die Methode REMOVE auf
	public Object remove() {
		Object item;

		try {
			// Versuche, die Anzahl belegter Pl�tze zu erniedrigen. Falls auf
			// Null ==> Warten!
			B.acquire();
			// Buffer f�r Zugriff gesperrt?
			mutex_S.lockInterruptibly();
		} catch (InterruptedException e) {
			// Erneutes Setzen des Interrupt-Flags
			Thread.currentThread().interrupt();
		}

		if (Thread.currentThread().isInterrupted()) {
			return null;
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
		mutex_S.unlock();
		// Anzahl freier Pl�tze erh�hen und ggf.wartenden Producer wecken
		F.release();

		return item;
	}

}
