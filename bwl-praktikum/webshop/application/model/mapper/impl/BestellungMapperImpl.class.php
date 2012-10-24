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

    private static $query_getBestellungById = "SELECT * FROM bestellung WHERE bestellnr = '%s'";
    private static $query_getListOfBestellungByKundeId = "SELECT * FROM bestellung WHERE kundenr = '%s'";
    private static $dbm;
    
    public function __construct() {
        $this->dbm = DBManager::create();
    } 
    
    public static function getBestellungById($bestellNr) {
        $sql = printf(self::$query_getBestellungById, $bestellNr);
        
        return $this->dbm->query($sql);
    }

    public static function getListOfBestellungByKundeId($kundeId) {
        $sql = printf(self::$query_getListOfBestellungByKundeId, $kundeId);
        
        return $this->dbm->query($sql);
    }

    
}

?>
