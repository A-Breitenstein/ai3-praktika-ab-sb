<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abg667
 */
interface TeileMapper {
    
    public static function getTeileById($tNr);
    
    public static function getListOfTeileByBezeichung($bezeichnung);
    
    public static function getListOfUnterteilOfTeileById($tNr);
    
    public static function getOberteilOfTeileById($tNr);
}

?>
