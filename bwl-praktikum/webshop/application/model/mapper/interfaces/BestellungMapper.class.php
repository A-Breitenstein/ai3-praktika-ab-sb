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
     * @param $bestellNr
     */
    public function getBestellungById($bestellNr);
    
    /**
     * Gibt die Bestellungen zurück, die zur $kundeId gehören.
     *
     * @param $kundeId
     */
    public function getListOfBestellungByKundeId($kundeId);
}

?>
