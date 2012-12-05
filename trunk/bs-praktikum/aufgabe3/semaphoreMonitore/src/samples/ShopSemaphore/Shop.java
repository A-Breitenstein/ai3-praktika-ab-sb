/**
 * Shop.java
 */
package samples.ShopSemaphore;
import java.util.concurrent.*;

public class Shop {
  private static final int ANZAHL_KOERBE = 2;

  private Semaphore Korbstapel;

  public Shop() {
    Korbstapel = new Semaphore(ANZAHL_KOERBE);
  }

  // Customer ruft die Methode ENTER auf
  public void enter() {
    // Versuche, einen Korb zu bekommen. Falls Stapel auf Null ==> Warten!
    try {
      Korbstapel.acquire();
    } catch (InterruptedException e) {
      // Erneutes Setzen des Interrupt-Flags
      Thread.currentThread().interrupt();
    }
    if (Thread.currentThread().isInterrupted()) {
      return;
    }
    // Einkaufen
    System.err
        .println("                                             Customer "
            + Thread.currentThread().getName() + " buys goods!");
    buyGoods();

    // Laden verlassen
    System.err
        .println("                                                                                   Customer "
            + Thread.currentThread().getName() + " leaves shop!");
    Korbstapel.release();
  }

  // Customer benutzen diese Methode, um einzukaufen
  public void buyGoods() {
    int sleepTime = (int) (1000 * Math.random());

    try {
      Thread.sleep(sleepTime);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

}
