<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of TeileMapperImpl
 *
 * @author abg667
 */
class TeileMapperImpl implements TeileMapper {

    private $query_getListOfTeileByBezeichung = "SELECT * FROM teile WHERE bezeichung LIKE '%s'";
    private $query_getListOfUnterteilOfTeileById = "SELECT * FROM teile t, struktur s WHERE t.'%s' = s.otnr";
    private $query_getOberteilOfTeileById = "SELECT * FROM teile t, struktur s WHERE t.'%s' = s.utnr";
    private $query_getTeileById = "SELECT * FROM teile WHERE tnr = '%s'";
    private $query_getAllTeileSelected = "SELECT * FROM teile WHERE verkaufstyp = '%s'";
    private $query_getAllTeile = "SELECT * FROM teile";
    private $query_getListOfTeileByBestellungId = "SELECT b.bestellnr, t.tnr,t.bezeichung,t.preis,t.typ,t.verkaufstyp,t.bild,t.beschreibung 
                                                   FROM bestellung b, teile t, bestellung_teile bt 
                                                   WHERE b.bestellnr = bt.bestellnr and t.tnr = bt.tnr and b.bestellnr = '%s'";
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
    
    public function getListOfTeileByBezeichung($bezeichnung) {

       $sql = sprintf($this->query_getListOfTeileByBezeichung,"%".$bezeichnung."%");
        return $this->createListeOfTeilWithAllCollumns($sql);
    }

    public function getListOfUnterteilOfTeileById($tNr) {
        $sql = printf($this->query_getListOfUnterteilOfTeileById, $tNr);

        return $this->dbm->query($sql);
    }

    public function getOberteilOfTeileById($tNr) {
        $sql = printf(self::$query_getOberteilOfTeileById, $tNr);

        return $this->dbm->query($sql);
    }

    public function getTeileById($tNr) {
        $sql = printf(self::$query_getTeileById, $tNr);

        return $this->dbm->query($sql);
    }
    
    private function getAllTeile($bool) {
        
        $sql = printf($this->query_getAllTeileSelected, $bool);
        
        return $this->createListeOfTeilWithAllCollumns($sql); 
    }
    
    public function getProdukte() {
        return $this->getAllTeile(true); 
    }
    
    public function getAlleProdukte() {
        $sql = $this->query_getAllTeile;
        
        return $this->createListeOfTeilWithAllCollumns($sql);
    }

    public function getListOfTeileByBestellungId($bestellnr) {
        $sql = printf($this->query_getListOfTeileByBestellungId, $bestellnr);
        
        return $this->createListeOfTeilWithAllCollumns($sql);
    }
    
    private function createListeOfTeilWithAllCollumns($sql){
        $result = $this->dbm->query($sql);
        $ListOfTeile = array();
        while($row = mysql_fetch_array($result,MYSQL_ASSOC)){
            
            $teile = Teile::neu($row['tnr'], $row['bezeichnung'], $row['preis'],$row['typ'], $row['verkaufstyp'], $row['bild'],$row['beschreibung']);
            
            array_push($ListOfTeile, $teile);
        }
        return $ListOfTeile;
        
    }


}

?>
