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
    
    public static function getBestellungById($bestellNr);
    
    public static function getListOfBestellungByKundeId($kundeId);
}

?>
