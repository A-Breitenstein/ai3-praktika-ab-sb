<?php
class StrukturMapperImpl{
    private $query_strukturAnlegen = "INSERT INTO `struktur` (`OTNr` , `UTNr` , `menge`)
                                    VALUES ('%s', '%s', '%s')";
    private $query_getAlleStrukturen = "SELECT * FROM `struktur`";
    private $dbm;
    public function __construct() {
        $this->dbm = DBManager::create();
        $this->dbm->connect();
    }

    public function __destruct() {
        $this->dbm->close();
    }

    public static function make(){
        $name = __CLASS__;
        return new $name();
    }

    public function strukturAnlegen($OTNr,$UTNr,$menge){
        $sql = sprintf($this->query_strukturAnlegen,$OTNr,$UTNr,$menge);
        $this->dbm->query($sql);
    }
    public function getAlleStrukturen(){
        $sql = $this->query_getAlleStrukturen;
        $result = $this->dbm->query($sql);
        $ListOfStrukturen = array();
        $ListOfTeile = array();
        $teileMapper = TeileMapperImpl::make();
        //mysql_fetch_array($result,MYSQL_ASSOC)
        while($row = mysql_fetch_assoc($result)){

            $oberteil= $teileMapper->getTeileById($row['OTNr']);
            $unterteil = $teileMapper->getTeileById($row['UTNr']);
            $oberteil = Stueckliste::createTeil($oberteil->getTeileNr(),$oberteil->getBezeichnung(),$oberteil->getPreis());
            $unterteil = Stueckliste::createTeil($unterteil->getTeileNr(),$unterteil->getBezeichnung(),$unterteil->getPreis());
            array_push($ListOfTeile,$oberteil,$unterteil);
            $struktur = Stueckliste::createStruktur(
                            $oberteil,
                            $unterteil,
                            $row['menge']);

            array_push($ListOfStrukturen, $struktur);
        }
        Stueckliste::$listeVonStrukturen = $ListOfStrukturen;
        Stueckliste::$listeVonTeilen =$ListOfTeile;
    }
}
?>