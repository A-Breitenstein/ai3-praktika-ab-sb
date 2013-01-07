package simulation;
import osbsp.OperatingSystem;
import osbsp.Statistics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulation eines Hauptspeicherverwaltungssystems auf Basis eines
 * "virtuellen Speichers" mit Demand Paging, lokaler Seitenersetzungsstrategie
 * und fester Hauptspeicherzuteilung pro Prozess.
 * 
 * Initialisierung der Simulationsumgebung, Start/Ende der Simulation und
 * Auswertung
 * 
 * @author Martin H�bner
 */
public class SimulationEnv {

	/**
	 * Dauer der Simulation in Millisekunden
	 */
	public static int simulationTime;

	/**
	 * Anzahl an erzeugten Prozessen (1 reicht f�r die Auswertung der
	 * Seitenfehlerrate)
	 */
	public static final int NUM_OF_PROCESSES = 1;

	/**
	 * Main-Methode zum Start der Simulation
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
        int[] default_locality_factors ={1,10,100,1000,10,10};
        int[] max_ram_pages ={10,10,10,10,15,20};
        List<Statistics> results = new ArrayList<Statistics>();
        boolean testmode = true;
        for (OperatingSystem.ImplementedReplacementAlgorithms implementedReplacementAlgorithms : OperatingSystem.ImplementedReplacementAlgorithms.values()) {
            System.out.println("------------" + implementedReplacementAlgorithms + "------------");
            for (int i = 0; i < default_locality_factors.length; i++) {

                final int tmp_max_ram_pages = max_ram_pages[i];
                final int tmp_default_loality_factor = default_locality_factors[i];
                results.add(testdurchlauf(testmode, tmp_max_ram_pages, tmp_default_loality_factor, implementedReplacementAlgorithms));
            }
        }

        try {
            FileWriter fout = new FileWriter("results.csv");
            int i = 1;
            fout.write("Max_RAM_PAGESM;LOCALITY_FACTOR;PAGEFAULTS\n");
            for (Statistics result : results) {
                fout.write(result.max_ram_pages_per_process+";"+result.default_locality_factor+";"+result.getPageFaults()+"\n");
            }
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private static Statistics testdurchlauf(boolean test_mode,int max_ram_pages_per_process,int default_locality_factor,OperatingSystem.ImplementedReplacementAlgorithms paging_algo) {
        int pid; // Aktuelle Prozess-ID

        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        // "Laden" des Betriebssystems
        OperatingSystem os = new OperatingSystem();
        System.out
                .println("*********** Simulation der Betriebssystem-Speicherverwaltung startet *************");

        // ------------------------- Parameter setzen
        // ----------------------------------------------
        // Dauer der Simulation in ms
        simulationTime = 10000;
        // max. Anzahl Seiten pro Prozess im Hauptspeicher (sonst Verdr�ngung eigener Seiten)
        os.setMAX_RAM_PAGES_PER_PROCESS(max_ram_pages_per_process);
        // CLOCK oder FIFO oder RANDOM
        os.setREPLACEMENT_ALGORITHM(paging_algo);
        // Anzahl Operationen innerhalb eines Seitenbereichs
        os.setDEFAULT_LOCALITY_FACTOR(default_locality_factor);

        // Testausgaben erw�nscht? Wenn true, dann Dauer auf max. 100 ms setzen!
        os.setTestMode(test_mode);
        os.eventLog.default_locality_factor = default_locality_factor;
        os.eventLog.max_ram_pages_per_process = max_ram_pages_per_process;
        // ------------------------- Parameter setzen Ende
        // ------------------------------------------

        // Erzeugen von unabh�ngigen Prozessen
        for (int i = 0; i < NUM_OF_PROCESSES; i++) {
            pid = os.createProcess(5120); // 20 Seiten bei einer Seitengr��e von
            // 256 KB
            if (pid < 0) {
                System.out
                        .println("*********** Fehlerhafte Konfiguration: Zu wenig RAM f�r "
                                + NUM_OF_PROCESSES + " Prozesse! *************");
                break;
            }
        }
        // Laufzeit abwarten
        try {
            Thread.sleep(simulationTime);
        } catch (InterruptedException e) {
        }
        // Alle Prozesse stoppen
        os.killAll();

        System.out
                .println("*********** Simulation der Betriebssystem-Speicherverwaltung wurde nach "
                        + simulationTime + " ms beendet *************");

        // Statistische Auswertung anzeigen
        os.eventLog.showReport();
        return os.eventLog;
    }
}
