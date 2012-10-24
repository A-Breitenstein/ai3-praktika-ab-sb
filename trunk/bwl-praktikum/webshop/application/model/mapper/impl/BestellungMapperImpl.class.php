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
        $sql = printf(self::$query_getBestellungById, $bestellNr);
        
       $row = $this->dbm->fetch_assoc($sql);
       $bestellung = Bestellung::neu($row['bestellnr'], $row['kundennr'], $row['bestelldatum']);
    }

    public function getListOfBestellungByKundeId($kundeId) {
        $sql = printf(self::$query_getListOfBestellungByKundeId, $kundeId);
        
        $result = $this->dbm->query($sql);
        $ListOfBestellung = array();
        while($row = mysql_fetch_array($result,MYSQL_ASSOC)){
            
            $bestellung = Bestellung::neu($row['bestellnr'], $row['kundennr'], $row['bestelldatum']);
            
            array_push($ListOfBestellung, $bestellung);
        }
        return $ListOfBestellung; 
    }
    
    

    
}

?>
