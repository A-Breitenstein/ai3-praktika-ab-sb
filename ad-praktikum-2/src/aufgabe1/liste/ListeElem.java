package aufgabe1.liste;

/**
 * User: Alex
 * Date: 26.10.13
 * Time: 23:52
 */
public class ListeElem<ElemType> {

    private ElemType elem;

    private ListeElem nextElem;

    private ListeElem(ElemType elem) {
        this.elem = elem;
    }

    public static <ElemType> ListeElem<ElemType> create(ElemType elem) {
        return new ListeElem<ElemType>(elem);
    }

    public ElemType getElem() {
        return elem;
    }

    public void setElem(ElemType elem) {
        this.elem = elem;
    }

    public void setNextElem(ListeElem nextElem) {
        this.nextElem = nextElem;
    }

    public ListeElem<ElemType> getNextElem() {
        return nextElem;
    }

    @Override
    public String toString() {
        return "ListeElem{" +
                "elem=" + elem +
                ", nextElem=" + nextElem +
                '}';
    }
}
