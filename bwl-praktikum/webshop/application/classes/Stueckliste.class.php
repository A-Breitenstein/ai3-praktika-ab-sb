<?php
class Stueckliste{
    public static $listeVonTeilen;
    public static $listeVonStrukturen;

    public static function getProdukt($produktname){
        foreach(self::$listeVonTeilen as $teil){
            if($teil->name() == $produktname)
                return $teil;
        }
    }
    public static function createStruktur($oberteil,$unterteil,$menge){
        return new Struktur_impl($oberteil,$unterteil,$menge);
    }
    public static function createTeil($id,$name,$preis){
        return new Teil_impl($id,$name,$preis);
    }
    public static function bestimmeAlleUnterteileVon($teil){

    }

    public static function ermittelBenoetigteTeileVonTeilX($teilX,$listeVonStrukturen){
        if(!$teilX instanceof Teil_impl) die("Stueckliste::ermittelBenoetigteTeileVonTeilX:: wrong argument");

        $contactArray = array();
        foreach($listeVonStrukturen as $struktur){
          if($struktur->oberteil()->id() == $teilX->id()){
            array_push($contactArray,$struktur);
          }
        }
    }
    public static function bestimmeOberUndUnterteile($listeVonTeilen,$listeVonStrukturen){
        foreach($listeVonTeilen as $teil){
            foreach($listeVonStrukturen as $struktur){
                if($teil->id() == $struktur->oberteil()->id()){
                    $teil->addIstOberteilInStruktur($struktur);
                }
                if($teil->id() == $struktur->unterteil()->id()){
                    $teil->addIstUnterteilInStruktur($struktur);
                }
            }
        }

    }
   public static function printTeil($teil){
       echo("<div style='width: 100%;'>");
       echo("<div style='float:left;width:100%;border: 1px solid black;'><div style='float:left;width:15%;margin-left: 3%;'> Name </div><div style='float:right;margin-right:3%;'>Menge</div></div>\n");

       self::_runDown($teil,0,1);
       echo("</div>");
   }
    private static function _runDown($unterteil,$depth,$menge){
        echo("<div onmouseout='javascript:unhighlight_row(this);' onmouseover='javascript:highlight_row(this);' style='float:left;width:100%;border: 1px solid black;'><div onclick=\"location.href='products?search=".$unterteil->name()."'\" style='float:left;cursor:pointer;margin-left:".(30*$depth)."px;'>".$unterteil->name()."</div><div style='float:right;margin-right:3%;'>".$menge."</div></div>\n");

        if($unterteil->getIstOberteilInStruktur() != null){
            foreach($unterteil->getIstOberteilInStruktur() as $struktur){
                if($struktur->oberteil()->id() == $unterteil->id())
                    self::_runDown($struktur->unterteil(),$depth+1,$struktur->menge());
            }
        }
    }
    private static function berechneMengen(){

    }
    private static function generateSpaces($count){
        $result = "";
        for($i = 0;$i < $count*3; $i++){
            $result=$result."-";
        }
        return $result;
    }
    public static function  stueckliste_test(){
        $p1 = Stueckliste::createTeil(1,"Produkt1",null);
        $p2 = Stueckliste::createTeil(2,"Produkt2",null);
        $b1 = Stueckliste::createTeil(3,"Baugruppe1",null);
        $b2 = Stueckliste::createTeil(4,"Baugruppe2",null);
        $b3 = Stueckliste::createTeil(5,"Baugruppe3",null);
        $b4 = Stueckliste::createTeil(6,"Baugruppe4",null);
        $b5 = Stueckliste::createTeil(7,"Baugruppe5",null);
        $b6 = Stueckliste::createTeil(8,"Baugruppe6",null);
        $a = Stueckliste::createTeil(9,"TeilA",300);
        $b = Stueckliste::createTeil(10,"TeilB",50);
        $c = Stueckliste::createTeil(11,"TeilC",2);
        $d = Stueckliste::createTeil(12,"TeilD",1500);
        $e = Stueckliste::createTeil(13,"TeilE",800);
        $f = Stueckliste::createTeil(14,"TeilF",20);
        $g = Stueckliste::createTeil(15,"TeilG",130);

        $listeVonTeilen = array();
        array_push($listeVonTeilen,$p1,$p2,$b1,$b2,$b3,$b4,$b5,$b6,$a,$b,$c,$d,$e,$f,$g);

        $listeVonStrukturen = array();
        array_push($listeVonStrukturen,
            Stueckliste::createStruktur($p1,$b1,2),
            Stueckliste::createStruktur($p1,$b2,1),
            Stueckliste::createStruktur($p2,$e,1),
            Stueckliste::createStruktur($p2,$b,10),
            Stueckliste::createStruktur($p2,$b4,3),
            Stueckliste::createStruktur($p2,$g,100),
            Stueckliste::createStruktur($b1,$a,1),
            Stueckliste::createStruktur($b1,$f,20),
            Stueckliste::createStruktur($b1,$c,50),
            Stueckliste::createStruktur($b2,$f,10),
            Stueckliste::createStruktur($b2,$b3,2),
            Stueckliste::createStruktur($b4,$c,25),
            Stueckliste::createStruktur($b4,$b5,4),
            Stueckliste::createStruktur($b3,$d,1),
            Stueckliste::createStruktur($b3,$e,1),
            Stueckliste::createStruktur($b5,$b6,5),
            Stueckliste::createStruktur($b5,$a,1),
            Stueckliste::createStruktur($b6,$c,20),
            Stueckliste::createStruktur($b6,$f,2)
        );

        Stueckliste::bestimmeOberUndUnterteile($listeVonTeilen,$listeVonStrukturen);
        self::$listeVonStrukturen = $listeVonStrukturen;
        self::$listeVonTeilen = $listeVonTeilen;
//        echo("<br>");
//         self::printTeil($p1);
//         self::printTeil($p2);
//        var_dump($p1->getIstOberteilInStruktur());
    }


    public static function convertTeileInStuecklistenTeile($listeVonTeile){
        if(!is_array($listeVonTeile)) die("Stueckliste::convertTeileInStueklistenTeile:: illegal argument exception");
        $resultSet = array();
        foreach($listeVonTeile as $teil){
            array_push($resultSet,self::createTeil($teil->getTeileNr(),$teil->getBezeichnung(),$teil->getPreis()));
        }
        return $resultSet;
    }

}
interface Struktur{
    function oberteil();
    function unterteil();
    function menge();
}

interface Teil{
    function id();
    function name();
    function preis();
    function addIstOberteilInStruktur($struktur);
    function getIstOberteilInStruktur();
    function addIstUnterteilInStruktur($struktur);
    function getIstUnterteilInStruktur();
}
class Struktur_impl implements Struktur{
    private $oberteil;
    private $unterteil;
    private $menge;

    function oberteil()
    {
        return $this->oberteil;
    }

    function unterteil()
    {
        return $this->unterteil;
    }

    function menge()
    {
        return $this->menge;
    }
    function __construct($oberteil,$unterteil,$menge){
       $this->oberteil = $oberteil;
       $this->unterteil = $unterteil;
       $this->menge = $menge;
    }
}

class Teil_impl implements Teil{
    private $id;
    private $name;
    private $preis;
    private $hierBinIchUnterteil = array();
    private $hierBinIchOberteil = array();

    function __construct($id,$name,$preis){
        $this->id = $id;
        $this->name = $name;
        $this->preis = $preis;
    }
    function id()
    {
        return $this->id;
    }

    function name()
    {
        return $this->name;
    }

    function preis()
    {
        return $this->preis;
    }

    function addIstOberteilInStruktur($struktur)
    {
        array_push($this->hierBinIchOberteil,$struktur);
    }

    function getIstOberteilInStruktur()
    {
        return $this->hierBinIchOberteil;
    }

    function addIstUnterteilInStruktur($struktur)
    {
        array_push($this->hierBinIchUnterteil,$struktur);
    }

    function getIstUnterteilInStruktur()
    {
        return $this->hierBinIchUnterteil;
    }
}
?>