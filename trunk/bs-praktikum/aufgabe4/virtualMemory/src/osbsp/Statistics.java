package osbsp;
/**
 * Sammlung und Auswertung statistischer Daten eines Simulationslaufs
 */
public class Statistics {
    public int max_ram_pages_per_process = 0;
    public int default_locality_factor = 0;
	private int pageFaults;			// Anzahl Seitenfehler
	private int writeAccesses;		// Anzahl Schreibzugriffe
	private int readAccesses;		// Anzahl Lesezugriffe

	//	Seitenfehlerrrate = Anzahl Seitenfehler / Anzahl Zugriffe
	private float pageFaultRate;	
				
	/**
	 * Konstruktor
	 */
	public Statistics() {
		resetCounter();
	}

	/**
	 * Alle Statistik-Z�hler zur�cksetzen
	 */
	public void resetCounter() {
		pageFaults = 0;
		writeAccesses = 0;
		readAccesses = 0;
		pageFaultRate = 0;		
	}
	
	/**
	 * @return Seitenfehlerrrate = Anzahl Seitenfehler / Anzahl Zugriffe
	 */
	public float getPageFaultRate() {
		pageFaultRate = (float) pageFaults / (writeAccesses + readAccesses);
		return pageFaultRate;
	}

	/**
	 * @return Anzahl Seitenfehler
	 */
	public int getPageFaults() {
		return pageFaults;
	}

	/**
	 * @return Anzahl Zugriffe insgesamt
	 */
	public int getTotalAccesses() {
		return readAccesses + writeAccesses;
	}
	
	/**
	 * @return Anzahl Lesezugriffe
	 */
	public int getReadAccesses() {
		return readAccesses;
	}

	/**
	 * @return Anzahl Schreibzugriffe 
	 */
	public int getWriteAccesses() {
		return writeAccesses;
	}

	/**
	 * Seitenfehler z�hlen
	 */
	public void incrementPageFaults() {
		pageFaults++;
	}

	/**
	 * Lesezugriff z�hlen
	 */
	public void incrementReadAccesses() {
		readAccesses++;
	}

	/**
	 * Schreibzugriff z�hlen
	 */
	public void incrementWriteAccesses() {
		writeAccesses++;
	}

	/**
	 *  Statistik-Bericht auf der Console ausgeben
	 *
	 */
	public void showReport() {
		System.out.println("\n**************** Statistik *************************");
		System.out.println("*** Anzahl Seitenfehler: "+getPageFaults());
		System.out.println("*** Anzahl Zugriffe:     "+getTotalAccesses());
		System.out.println("*** Seitenfehlerrate:    "+getPageFaultRate());
		System.out.println("*** Seiten im RAM:       "+max_ram_pages_per_process);
		System.out.println("*** Lokalitaetsfaktor:   "+default_locality_factor);
		System.out.println("****************************************************");
	}
}
