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
    public function getAlleProdukte();
    /**
     * Gibt alle Teile zurück;
     * 
     */

    //public function getAllTeile();
    
    /**
     * Gibt das Teil anhand der Teilnummer zurück. 
     * 
     */
    public function getTeileById($tNr);
    
    /**
     * Gibt eine Liste von Teilen zurück, ähnlich der Bezeichnung.
     * 
     */
    public function getListOfTeileByBezeichnung($bezeichnung);
    
    /**
     * Gibt eine Liste von Unterteilen anhand der Teilnummer zurück.
     * 
     */
    public function getListOfUnterteilOfTeileById($tNr);
    
    /**
     * Gibt das Oberteil anhand der Teilnummer zurück. 
     * 
     */
    public function getOberteilOfTeileById($tNr);
    
    public function getListOfTeileByBestellungId($bestellnr);
}

?>
