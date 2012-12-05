/**
 * PipeServer.java
 */
package samples.ErzVerbrPipes;
import java.io.*;

public class PipeServer {
	public static void main(String args[]) {

		// Pipes erzeugen
		PipedInputStream inPipe = new PipedInputStream();
		PipedOutputStream outPipe = new PipedOutputStream();
		try {
			outPipe.connect(inPipe);
		} catch (IOException e) {
		}

		// Producer und Consumer - Threads erzeugen
		Producer p = new Producer(outPipe);
		Consumer c = new Consumer(inPipe);
		p.start();
		c.start();

		// Laufzeit abwarten
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
		}

		p.interrupt();
		c.interrupt();

		try {
			inPipe.close();
			outPipe.close();
		} catch (IOException e) {
		}

		System.err.println("-------------------- THE END -------------------");
	}

}
