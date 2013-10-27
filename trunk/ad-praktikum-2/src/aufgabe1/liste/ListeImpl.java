package aufgabe1.liste;

import sun.org.mozilla.javascript.internal.ScriptRuntime;

/**
 * User: Alex
 * Date: 26.10.13
 * Time: 23:48
 */
public class ListeImpl<ElemType> implements Liste<ElemType>{

    private int length;

    private ListeElem<ElemType> firstElem;

    private long timeBeforeOperation;
    private long timeAfterOperation;

    public ListeImpl() {
        this.length = 0 ;
    }

    @Override
    public void cons(ElemType elem) {
        timeBeforeOperation = System.nanoTime();

        ListeElem<ElemType> listeElem = ListeElem.create(elem);
        listeElem.setNextElem(firstElem);
        length++;
        this.firstElem = listeElem;

        timeAfterOperation = System.nanoTime();
    }

    @Override
    public void head() {
        if (length > 0) {
            this.firstElem = firstElem.getNextElem();
            length--;
        }
    }

    @Override
    public int lenght() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public void insert(ElemType elem, int index) {
        timeBeforeOperation = System.nanoTime();
        if (!(index < 0)) {

            //Vorne Einfügen, wenn der index 0 entspricht
            if (index == 0) {
                cons(elem);
            }
            // d.h. Index == lenght ist direkt nach dem Ende der Liste, alles größer wird an das Ende angehangen
            else if (index >= length) {

                if (length != 0) {
                    ListeElem listeElem = firstElem;
                    for (int i = 1; i < length; i++) {
                        listeElem = listeElem.getNextElem();
                    }

                    listeElem.setNextElem(ListeElem.create(elem));
                    length++;
                }else{
                    cons(elem);
                }
            }


            //Ein Element wird innerhalb der Liste eingefügt, eine umhängung der Elemente ist von nöten
            else if (index < length) {

                //Es wird das aktuelle und das nächste Element gebraucht,
                //um die Referenz auf den Rest der Liste zu erhalten
                ListeElem listeElem = firstElem;
                ListeElem nextListeElem = listeElem.getNextElem();

                for (int i = 1; i < index; i++) {
                    listeElem = nextListeElem;
                    nextListeElem = listeElem.getNextElem();
                }

                ListeElem newListeElem = ListeElem.create(elem);
                newListeElem.setNextElem(nextListeElem);
                listeElem.setNextElem(newListeElem);

                length++;
            }
        }
        timeAfterOperation = System.nanoTime();
    }

    @Override
    public double getTime(TimeType timeType) {
        long nanoseconds = timeAfterOperation - timeBeforeOperation;

        if (!(timeType.equals(TimeType.NANO))) {
            if(timeType.equals(TimeType.MILLI))
                return nanoseconds / 1000000.0;

            if(timeType.equals(TimeType.SECOND))
                return getTime(TimeType.MILLI) /1000.0;
        }

        return nanoseconds;

    }
}
