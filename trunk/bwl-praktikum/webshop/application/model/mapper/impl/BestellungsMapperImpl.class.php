<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *
 * @author abg667
 */
class BestellungMapperImpl {

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



}

?>
