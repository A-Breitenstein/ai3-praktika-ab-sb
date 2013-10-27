package aufgabe1.liste;

/**
 * User: Alex
 * Date: 26.10.13
 * Time: 23:48
 */
public interface Liste<ElemType> {

    /**
     * Fügt Element an die erste Stelle der Liste ein
     * @param elem vom Typ der Liste
     */
    public void cons(ElemType elem);

    /**
     * Köpft die Liste, entfernt das erste Element der Liste
     */
    public void head();

    /**
     * Giebt die Länge der Liste zurück
     * @return int [0-n]
     */
    public int lenght();

    /**
     * überprüft anhand der Länge ob diese Leer ist: length == 0
     * @return true | false
     */
    public boolean isEmpty();

    /**
     * Fügt ein Element an angegebenen Index hinzu
     * @param elem Element welches hinzugefügt wird
     * @param index welcher angiebt an welche Stelle das Element kommt
     */
    public void insert(ElemType elem, int index);

    /**
     * Giebt die Zeit einer Operation wieder, sollte nach jeder Operation aufgerufen werden, da sonst die Werte
     * überschrieben werden, gemessen werden folgende Operationen:
     * -cons()/1
     * -head()/0
     * -insert()/2
     *
     * @param timeType Benötigt um Zeit in bestimmter größe zurückzugeben, TimeType: {NANO, MILLI, SECOND}
     * @return double Wert je nach TimeType
     */
    public double getTime(TimeType timeType);
}
