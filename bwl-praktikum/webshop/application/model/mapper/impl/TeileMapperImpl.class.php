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
    
    private static $query_getListOfTeileByBezeichung = "SELECT * FROM teile WHERE bezeichung LIKE = '%%s%'";
    private static $query_getListOfUnterteilOfTeileById = "SELECT * FROM teile t, struktur s WHERE t.'%s' = s.otnr"; 
    private static $query_getOberteilOfTeileById = "SELECT * FROM teile t, struktur s WHERE t.'%s = s.utnr"; 
    private static $query_getTeileById = "SELECT * FROM teile WHERE tnr = '%s'";
    private static $dbm;
    
    public function __construct() {
        $this->dbm = DBManager::create();
    }
    
    public static function getListOfTeileByBezeichung($bezeichnung) {
        $sql = printf(self::$query_getListOfTeileByBezeichung, $bezeichnung);
        
        return $this->dbm->query($sql);
    }

    public static function getListOfUnterteilOfTeileById($tNr) {
        $sql = printf(self::$query_getListOfUnterteilOfTeileById, $tNr);
        
        return $this->dbm->query($sql);
    }

    public static function getOberteilOfTeileById($tNr) {
        $sql = printf(self::$query_getOberteilOfTeileById, $tNr);
        
        return $this->dbm->query($sql);
    }

    public static function getTeileById($tNr) {
        $sql = printf(self::$query_getTeileById, $tNr);
        
        return $this->dbm->query($sql);
    }

}

?>
