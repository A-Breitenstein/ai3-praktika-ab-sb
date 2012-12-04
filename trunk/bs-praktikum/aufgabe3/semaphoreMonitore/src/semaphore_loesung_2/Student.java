package semaphore_loesung_2;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 04.12.12
 * Time: 16:01
 */
public class Student implements Runnable {
    private String name;
    private Kasse zugewieseneKasse;

    public Student(String name) {
        this.name = name;
    }

    public Student kasseZuweisen(Kasse kasse){
        System.out.println(name + " stellt sich an Kasse "+kasse.name());
        zugewieseneKasse = kasse;
        kasse.warteschlangenlaengeErhoehen();
        return this;
    }
    @Override
    public void run() {
        zugewieseneKasse.besetzten();
        System.out.println(name+" ist jetz an Kasse "+zugewieseneKasse.name()+" dran, .....");
        try {

            Thread.sleep(500);
            System.out.println((name + " verlässt die Kasse " + zugewieseneKasse.name()));
            zugewieseneKasse.freigeben();

            System.out.println(name + " beginnt zu essen ....");
            Thread.sleep(500);
            System.out.println(name+" ist fertig mit essen ");

        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Mensa.studentSuchtEineKasse(this);

    }

}
