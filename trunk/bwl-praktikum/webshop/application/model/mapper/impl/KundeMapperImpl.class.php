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

    private $query_getUserById = "SELECT * FROM kunde WHERE kundennr = '%s'";
    private $query_getUserByEmail = "SELECT * FROM kunde WHERE email = '%s'";
    private $query_kundeHinzufuegen = "INSERT INTO kunde(`Name`,`Vorname`,`eMail`) VALUES('%s','%s','%s')";
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
    
    public function getUserById($kundenNr) {
        $sql = sprintf($this->query_getUserById, $kundenNr);

        return $this->dbm->query($sql);
    }

    public function getUserByEmail($email) {
        $sql = sprintf($this->query_getUserByEmail, $email);

        return $this->dbm->query($sql);
    }
    public function kundeHinzufuegen($name,$vorname,$email){
        $sql = sprintf($this->query_kundeHinzufuegen,$name,$vorname,$email);
        return $this->dbm->query($sql);
    }

}

?>
