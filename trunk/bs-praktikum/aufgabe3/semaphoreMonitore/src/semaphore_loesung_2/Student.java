package semaphore_loesung_2;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 04.12.12
 * Time: 16:01
 */
public class Student implements Runnable {
    private String name;
    private Kasse zugewieseneKasse;
    private Random rand = new Random(System.currentTimeMillis());
    private static int MAX_BEZAHLZEIT = 1800;
    private static int MAX_ESSENSZEIT = 2220;

    public Student(String name) {
        this.name = name;
    }

    public Student kasseZuweisen(Kasse kasse){
        //System.out.println(name + " stellt sich an Kasse "+kasse.name());
        zugewieseneKasse = kasse;
        kasse.inWarteschlangeEinreihen(this);
        return this;
    }
    @Override
    public void run() {
        zugewieseneKasse.besetzten();
        if(zugewieseneKasse.werIstDerNaechste() != this){
            zugewieseneKasse.freigeben();
            System.out.println(name+" hat versucht an Kasse "+zugewieseneKasse.name()+" sich vorzudrängeln, zur strafe neu anstellen! ------>>");
            return;
        }



        //System.out.println(name+" ist jetz an Kasse "+zugewieseneKasse.name()+" dran, .....");
        try {

            Thread.sleep(rand.nextInt(MAX_BEZAHLZEIT));
            //System.out.println((name + " verlässt die Kasse " + zugewieseneKasse.name()));
            zugewieseneKasse.freigeben();

            //System.out.println(name + " beginnt zu essen ....");
            Thread.sleep(rand.nextInt(MAX_ESSENSZEIT));
            //System.out.println(name+" ist fertig mit essen ");

        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Mensa.studentSuchtEineKasse(this);

    }

}
