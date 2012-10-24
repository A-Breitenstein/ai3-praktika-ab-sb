<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of KundeMapper
 *
 * @author abg667
 */
class KundeMapperImpl implements KundeMapper {
    
    private static $query_getUserById = "SELECT * FROM kunde WHERE kundennr = '%s'";
    private static $query_getUserByEmail = "SELECT * FROM kunde WHERE email = '%s'";
    private static $dbm;
    
    public function __construct() {
        $this->dbm = DBManager::create();
    }
    
    public static function getUserById($kundenNr){
        $sql = printf(self::$query_getUserById, $kundenNr);
        
        return $this->dbm->query($sql);
    }
    
    public static function getUserByEmail($email){
        $sql = printf(self::$query_getUserByEmail, $email);
        
        return $this->dbm->query($sql);
    }
}

?>
