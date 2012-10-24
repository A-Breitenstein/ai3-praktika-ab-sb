<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abg667
 */
interface BestellungMapper {
    
    /**
     * Gibt die Bestellung zurück, die zur $bestellnr gehört.
     * 
     */
    public function getBestellungById($bestellNr);
    
    /**
     * Gibt die Bestellungen zurück, die zur $kundeId gehören.
     * 
     */
    public function getListOfBestellungByKundeId($kundeId);
}

?>
