<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of BestellungMapperImpl
 *
 * @author abg667
 */
class BestellungMapperImpl implements BestellungMapper {

    private $query_getBestellungById = "SELECT * FROM bestellung WHERE bestellnr = '%s'";
    private $query_getListOfBestellungByKundeId = "SELECT * FROM bestellung WHERE kundenr = '%s'";
    private $dbm;

    private $query_getBestellungenByKundenID = "SELECT b.`BestellNr`, b.`KundenNr`, b.`BestellDatum`,k.`Name`,k.`Vorname`,k.`eMail`,t.`TNr`,bt.`menge`,t.`Bezeichnung`,t.`Preis`
                                        FROM `bestellung` b ,`bestellung_teile` bt,`teile` t ,`kunde` k
                                        WHERE b.`KundenNr` = %s and b.`BestellNr` = bt.`bestellnr` and b.`KundenNr` = k.`KundenNr` and bt.`tnr` = t.`TNr`";

    
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
    
    public function getBestellungById($bestellNr) {
        $sql = sprintf(self::$query_getBestellungById, $bestellNr);
        
       $row = $this->dbm->fetch_assoc($sql);
       $bestellung = Bestellung::neu($row['bestellnr'], $row['kundennr'], $row['bestelldatum']);
    }

    public function getListOfBestellungByKundeId($kundeId) {
        $sql = sprintf(self::$query_getListOfBestellungByKundeId, $kundeId);
        
        $result = $this->dbm->query($sql);
        $ListOfBestellung = array();
        while($row = mysql_fetch_array($result,MYSQL_ASSOC)){
            
            $bestellung = Bestellung::neu($row['bestellnr'], $row['kundennr'], $row['bestelldatum']);
            
            array_push($ListOfBestellung, $bestellung);
        }
        return $ListOfBestellung; 
    }


    public function getBestellungenByKundenID($kundenNr) {
        $sql = sprintf($this->query_getBestellungenByKundenID, $kundenNr);

        $DB_result = $this->dbm->query($sql);

        /*
         * array(10) { ["BestellNr"]=> string(1) "1" ["KundenNr"]=> string(1) "1"
         *      ["BestellDatum"]=> string(19) "2012-12-05 19:15:15" ["Name"]=> string(6) "Bartel"
         *      ["Vorname"]=> string(4) "Sven" ["eMail"]=> string(12) "qwe123@wu.de"
         *      ["TNr"]=> string(1) "3" ["menge"]=> string(1) "2"
         *      ["Bezeichnung"]=> string(11) "Zylinder A5" ["Preis"]=> string(5) "22.80" }
         *
         * */
        $bestellung = null;
        $result = array();
        while($row = mysql_fetch_assoc($DB_result)){
            if(empty($bestellung) || ($bestellung->getBestellNr() != $row["BestellNr"])){
                $bestellung = Bestellung::neu($row["BestellNr"],$row["KundenNr"],$row["BestellDatum"]);
                $pushen = true;
            }
            $bestellung->addArtikel(Teile::neu(
                            $row["TNr"],
                            $row["Bezeichnung"],
                            $row["Preis"],null,null,null,null
            ),$row["menge"]);
            $bestellung->addDerKunde(Kunde::create(
                $row["KundenNr"],
                $row["Name"],
                $row["Vorname"],
                $row["eMail"],null)
            );
            if($pushen){
                array_push($result,$bestellung);
                $pushen = false;
            }
        }
        return $result;
    }
    

    
}

?>
