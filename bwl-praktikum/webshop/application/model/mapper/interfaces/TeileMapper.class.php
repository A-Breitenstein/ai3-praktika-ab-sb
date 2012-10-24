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
    
    /**
     * Gibt das Teil anhand der Teilnummer zurück. 
     * 
     */
    public static function getTeileById($tNr);
    
    /**
     * Gibt eine Liste von Teilen zurück, ähnlich der Bezeichnung.
     * 
     */
    public static function getListOfTeileByBezeichung($bezeichnung);
    
    /**
     * Gibt eine Liste von Unterteilen anhand der Teilnummer zurück.
     * 
     */
    public static function getListOfUnterteilOfTeileById($tNr);
    
    /**
     * Gibt das Oberteil anhand der Teilnummer zurück. 
     * 
     */
    public static function getOberteilOfTeileById($tNr);
}

?>
