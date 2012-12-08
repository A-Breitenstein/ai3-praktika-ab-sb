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

    private $query_getListOfTeileByBezeichnung = "SELECT * FROM teile WHERE bezeichnung LIKE '%s'";
    private $query_getListOfUnterteilOfTeileById = "SELECT * FROM teile t, struktur s WHERE t.'%s' = s.otnr";
    private $query_getOberteilOfTeileById = "SELECT * FROM teile t, struktur s WHERE t.'%s' = s.utnr";
    private $query_getTeileById = "SELECT * FROM teile WHERE tnr = '%s'";
    private $query_getAllTeileSelected = "SELECT * FROM teile WHERE angeboten = '%s'";
    private $query_getAllTeile = "SELECT * FROM teile";
    private $query_getListOfTeileByBestellungId = "SELECT b.bestellnr, t.tnr,t.bezeichnung,t.preis,t.typ,t.angeboten,t.bild,t.beschreibung
                                                   FROM bestellung b, teile t, bestellung_teile bt 
                                                   WHERE b.bestellnr = bt.bestellnr and t.tnr = bt.tnr and b.bestellnr = '%s'";
    private $query_teilErstellen = "INSERT INTO `teile` (`Bezeichnung` , `Preis` , `Typ` , `Angeboten` , `Bild` , `Beschreibung` )
                                    VALUES ('%s', '%s', '%s', %s , '%s', '%s' )";
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
    public function teilErstellen($bezeichnung,$preis,$typ,$angeboten,$bild,$beschreibung){
        $sql = sprintf($this->query_teilErstellen,$bezeichnung,$preis,$typ,$angeboten,$bild,$beschreibung);
        $this->dbm->query($sql);
    }

    public function getListOfTeileByBezeichnung($bezeichnung) {

       $sql = sprintf($this->query_getListOfTeileByBezeichnung,"%".$bezeichnung."%");
        return $this->createListeOfTeilWithAllCollumns($sql);
    }

    public function getListOfUnterteilOfTeileById($tNr) {
        $sql = sprintf($this->query_getListOfUnterteilOfTeileById, $tNr);
        return $this->createListeOfTeilWithAllCollumns($sql);
    }

    public function getOberteilOfTeileById($tNr) {
        $sql = sprintf($this->query_getOberteilOfTeileById, $tNr);

        return $this->dbm->fetch($sql);
    }

    public function getTeileById($tNr) {
        $sql = sprintf($this->query_getTeileById, $tNr);
        $row = $this->dbm->fetch_assoc($sql);
        return Teile::neu($row['TNr'], $row['Bezeichnung'], $row['Preis'],$row['Typ'], $row['Verkaufstyp'], $row['Bild'],$row['Beschreibung']);
    }
    
    private function getAllTeile($bool) {
        
        $sql = sprintf($this->query_getAllTeileSelected, $bool);
        
        return $this->createListeOfTeilWithAllCollumns($sql); 
    }
    
    public function getProdukte() {
        return $this->getAllTeile(true);
    }
    public function getAlleTeile(){
        return $this->getAllTeile(false);
    }
    public function getAlleProdukte() {
        $sql = $this->query_getAllTeile;
        
        return $this->createListeOfTeilWithAllCollumns($sql);
    }

    public function getListOfTeileByBestellungId($bestellnr) {
        $sql = sprintf($this->query_getListOfTeileByBestellungId, $bestellnr);
        
        return $this->createListeOfTeilWithAllCollumns($sql);
    }
    
    private function createListeOfTeilWithAllCollumns($sql){
        $result = $this->dbm->query($sql);
        $ListOfTeile = array();
        //mysql_fetch_array($result,MYSQL_ASSOC)
        while($row = mysql_fetch_assoc($result)){
            $teile = Teile::neu($row['TNr'], $row['Bezeichnung'], $row['Preis'],$row['Typ'], $row['Verkaufstyp'], $row['Bild'],$row['Beschreibung']);

            array_push($ListOfTeile, $teile);
        }
        return $ListOfTeile;
        
    }


}

?>
