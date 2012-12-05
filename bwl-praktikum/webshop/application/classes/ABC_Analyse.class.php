<?php
class ABC_Analyse{
    /**
     *  Kategorie A: 0%-75%  B: 75%- 95% C: 95%-100% )
     *
     */
    private static $categories = null;
    public static function categories(){
        if(empty(self::$categories))
            self::$categories = array("A" => new ABC_Intervall_impl("A",0.0,75.0),
                                      "B" => new ABC_Intervall_impl("B",75.0,95.0),
                                      "C" => new ABC_Intervall_impl("C",95.0,101.0));
        return self::$categories;


    }
    public static function createABC_ADT($id, $name, $preis, $verbrauch){
        return new ABC_ADT_impl($id, $name, $preis, $verbrauch);

    }
    public static function analyse(array $arrayOfABC_ADT){
        if(!is_array($arrayOfABC_ADT))
            die("ABC_Analyse::analyse:: given argument isnt an array");
        foreach($arrayOfABC_ADT as $abc_adt){
            if(!$abc_adt instanceof ABC_ADT)
                die("ABC_Analyse::analyse:: given array contains other elements then ABC_ADT");
        }

        //hier werden die Gesamtkosten akkumuliert und eine rückverkettung
        // mit der wholePriceMap gemacht (GesamtPreis => Produkt)
        $wholePriceMap = array();
        $wholePrice = 0;
        foreach($arrayOfABC_ADT as $abc_adt){
            $wholePrice =  $wholePrice + $abc_adt->wholePrice();
            $wholePriceMap[$abc_adt->wholePrice()] = $abc_adt;
        }
        // produktgesamtpreise ( verbrauch * stückpreis) absteigend sortieren
        $sortArray = array_keys($wholePriceMap);
        sort($sortArray,SORT_NUMERIC);
        $sortArray = array_reverse($sortArray);


        // prozentwert akkumulieren und abc kategorien bestimmen
        $percentageSummed = 0.0;
        $resultArray = array();
        foreach($sortArray as $descPrice){
            $percentageSummed = $percentageSummed + (($descPrice/$wholePrice)*100.0);
            foreach(self::categories() as $categorie){
                if($categorie->isBetween($percentageSummed))
                $wholePriceMap[$descPrice]->setCategorie($categorie->name());
            }

            array_push($resultArray,$wholePriceMap[$descPrice]);
        }

        return $resultArray;
    }

    public static function abc_analyse_test(){
        $listOfProducts = array();

        array_push($listOfProducts,
            ABC_Analyse::createABC_ADT(1,"ProduktA",300,4600),
            ABC_Analyse::createABC_ADT(2,"ProduktB",50,3000),
            ABC_Analyse::createABC_ADT(3,"ProduktC",2,432500),
            ABC_Analyse::createABC_ADT(4,"ProduktD",1500,1000),
            ABC_Analyse::createABC_ADT(5,"ProduktE",800,1300),
            ABC_Analyse::createABC_ADT(6,"ProduktF",20,61000),
            ABC_Analyse::createABC_ADT(7,"ProduktG",130,30000)
        );

        $result = ABC_Analyse::analyse($listOfProducts);
         return $result;
//        foreach($result as $abc_adt){
//            echo("Name: ".$abc_adt->productName() ." :: Kategorie: ".$abc_adt->categorie()." :: Preis: ".number_format($abc_adt->wholePrice(),2,',','.')."<br>");
//        }
    }
    public static function convertToABC_ADT($listeVonTeilen){
        $listeVonABC_ADT = array();
        foreach($listeVonTeilen as $teil){
            $abc_adt = ABC_Analyse::createABC_ADT($teil->getTeileNr(),$teil->getBezeichnung(),$teil->getPreis(),1);
            array_push($listeVonABC_ADT,$abc_adt);
        }
        return $listeVonABC_ADT;
    }

}

interface ABC_ADT{
    /**
     *  liefert die Verbrauchte Stückzahl des Produktes zurück
     * @return int
     */
    function consumption();

    /**
     * der Name des Produktes
     * @return string
     */
    function productName();

    /** die ID eines Produktes
     * @return int
     */
    function productID();

    /**
     * der Einkaufspreis
     * @return numeric
     */
    function price();

    /**
     * liefert den GesamtPreis ( Einkaufspreis * Verbrauchte_Stückzahl)
     * @return numeric
     */
    function wholePrice();

    /**
     * weißt dem Produkt eine Kategorie (A,B,C) zu
     * @param $categorie - die Kategorie
     * @return void
     */
    function setCategorie($categorie);

    /** gibt die Kategorie zurück
     * @return string
     */
    function categorie();
    /**
     * Erstellt ein ABC_ADT Objekt
     * @static
     * @param $id - ProduktID
     * @param $name - Produktname
     * @param $preis - Produktpreis
     * @param $verbrauch - Verbrauch / Stückzahl
     * @return ABC_ADT
     */
    static function create($id,$name,$preis,$verbrauch);
}
interface ABC_Intervall{
    /**
     * der Anfang des Intervalls
     * @return int
     */
    function start();

    /**
     * Ende des Intervalls
     * @return int
     */
    function end();

    /**
     * @param $val - der Prozentwert (Kategorie A: 0%-80%  B: 81%- 95% C: 96%-100% )
     * @return bool
     */
    function isBetween($val);

    /**
     *  Name der Kategorie
     * @return string
     */
    function name();
}

class ABC_ADT_impl implements ABC_ADT{

    private $consumption;
    private $name;
    private $ID;
    private $price;
    private $wholePrice;
    private $categorie;
    /**
     *  liefert die Verbrauchte Stückzahl des Produktes zurück
     * @return int
     */
    function consumption()
    {
        return $this->consumption;
    }

    /**
     * der Name des Produktes
     * @return string
     */
    function productName()
    {
        return $this->name;
    }

    /** die ID eines Produktes
     * @return int
     */
    function productID()
    {
        return $this->ID;
    }

    /**
     * der Einkaufspreis
     * @return numeric
     */
    function price()
    {
        return $this->price();
    }

    /**
     * liefert den GesamtPreis ( Einkaufspreis * Verbrauchte_Stückzahl)
     * @return numeric
     */
    function wholePrice()
    {
        return $this->wholePrice;
    }

    /**
     * weißt dem Produkt eine Kategorie (A,B,C) zu
     * @param $categorie - die Kategorie
     * @return void
     */
    function setCategorie($categorie)
    {
       $this->categorie = $categorie;
    }
    /**
     * Erstellt ein ABC_ADT Objekt
     * @static
     * @param $id - ProduktID
     * @param $name - Produktname
     * @param $preis - Produktpreis
     * @param $verbrauch - Verbrauch / Stückzahl
     * @return ABC_ADT
     */
    static function create($id, $name, $preis, $verbrauch)
    {
        return new ABC_ADT_impl($id,$name,$preis,$verbrauch);
    }
    function __construct($id, $name, $preis, $verbrauch){
       $this->ID = $id;
       $this->name = $name;
       $this->price = $preis;
       $this->consumption = $verbrauch;
       $this->wholePrice = $verbrauch * $preis;
    }

    /** gibt die Kategorie zurück
     * @return string
     */
    function categorie()
    {
       return $this->categorie;
    }
}

class ABC_Intervall_impl implements ABC_Intervall{
    private $start;
    private $end;
    private $name;
    /**
     * der Anfang des Intervalls
     * @return int
     */
    function start()
    {
       return $this->start;
    }

    /**
     * Ende des Intervalls
     * @return int
     */
    function end()
    {
        return $this->end;
    }

    /**
     * @param $val - der Prozentwert
     * @return bool
     */
    function isBetween($val)
    {
        return ($val > $this->start) && ($val <= $this->end);
    }

    /**
     *  Name der Kategorie
     * @return string
     */
    function name()
    {
        return $this->name;
    }
    function  __construct($name,$start,$end){
        $this->name = $name;
        $this->start = $start;
        $this->end = $end;
    }
}
?>