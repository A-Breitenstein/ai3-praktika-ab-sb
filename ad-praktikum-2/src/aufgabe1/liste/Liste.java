package aufgabe1.liste;

/**
 * User: Alex
 * Date: 26.10.13
 * Time: 23:48
 */
public interface Liste<ElemType> {

    public void cons(ElemType elem, Liste liste);

    public void head(Liste liste);

    public int lenght(Liste liste);

    public boolean isEmpty(Liste liste);

    public void insert(ElemType elem, int index, Liste liste);
}
