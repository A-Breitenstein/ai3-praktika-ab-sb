package semaphore_loesung_2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 04.12.12
 * Time: 16:05
 */
public class Kasse {
    private final String name;
    private Semaphore besetzt_mutex = new Semaphore(1,true);
    private int warteschlangenLaenge = 0;
    private Queue<Student> queue = new LinkedList<Student>();
    public Kasse(String name) {
        this.name = name;
    }

    public int getWarteschlangenlaenge(){
        return warteschlangenLaenge;
    }
    public void inWarteschlangeEinreihen(Student student){
        queue.add(student);
        warteschlangenLaenge++;

    }
    public void warteschlangenlaengeErniedrigen(){
        warteschlangenLaenge--;
    }
    public Student werIstDerNaechste(){
            return queue.element();
    }
    public void besetzten(){
        try {
            besetzt_mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public void freigeben(){
        queue.poll();
        warteschlangenlaengeErniedrigen();
        besetzt_mutex.release();
    }

    public String name(){
        return name;
    }
}
