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
     *
     * @return
     */
    public function getAlleProdukte();
    /**
     * Gibt alle Teile zurück;
     * 
     */

    //public function getAllTeile();
    
    /**
     * Gibt das Teil anhand der Teilnummer zurück. 
     *
     * @param $tNr
     * @return
     */
    public function getTeileById($tNr);
    
    /**
     * Gibt eine Liste von Teilen zurück, ähnlich der Bezeichnung.
     *
     * @param $bezeichnung
     * @return
     */
    public function getListOfTeileByBezeichnung($bezeichnung);
    
    /**
     * Gibt eine Liste von Unterteilen anhand der Teilnummer zurück.
     *
     * @param $tNr
     * @return
     */
    public function getListOfUnterteilOfTeileById($tNr);
    
    /**
     * Gibt das Oberteil anhand der Teilnummer zurück. 
     *
     * @param $tNr
     * @return
     */
    public function getOberteilOfTeileById($tNr);

    /**
     * @param $bestellnr
     * @return
     */
    public function getListOfTeileByBestellungId($bestellnr);
}

?>
