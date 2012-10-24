<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Bestellung
 *
 * @author abg667
 */
class Bestellung {
    private $bestellNr;
    private $kundenNr;
    private $bestellDatum;
    
    
    //Getter & Setter
    public function getBestellNr() {
        return $this->bestellNr;
    }

    public function setBestellNr($bestellNr) {
        $this->bestellNr = $bestellNr;
    }

    public function getKundenNr() {
        return $this->kundenNr;
    }

    public function setKundenNr($kundenNr) {
        $this->kundenNr = $kundenNr;
    }

    public function getBestellDatum() {
        return $this->bestellDatum;
    }

    public function setBestellDatum($bestellDatum) {
        $this->bestellDatum = $bestellDatum;
    }


}

?>
