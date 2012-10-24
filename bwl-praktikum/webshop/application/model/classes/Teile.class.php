<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Teile
 *
 * @author abg667
 */
class Teile {
    private $teileNr;
    private $bezeichung;
    private $preis;
    private $typ;
    private $verkaufstyp;
    private $bild;
    private $beschreibung;
    
    public function __construct($teileNr,$bezeichung,$preis,$typ,$verkaufstyp,$bild,$beschreibung) {
        $this->teileNr = $teileNr;
        $this->bezeichung = $bezeichung;
        $this->preis = $preis;
        $this->typ = $typ;
        $this->verkaufstyp = $verkaufstyp;
        $this->bild = $bild;
        $this->beschreibung = $beschreibung;
    }
    
    public static function neu($teileNr,$bezeichung,$preis,$typ,$verkaufstyp,$bild,$beschreibung){
        return new Teile($teileNr, $bezeichung, $preis, $typ, $verkaufstyp, $bild, $beschreibung);
    }
    
    //Getter & Setter
    public function getTeileNr() {
        return $this->teileNr;
    }

    public function setTeileNr($teileNr) {
        $this->teileNr = $teileNr;
    }

    public function getBezeichung() {
        return $this->bezeichung;
    }

    public function setBezeichung($bezeichung) {
        $this->bezeichung = $bezeichung;
    }

    public function getPreis() {
        return $this->preis;
    }

    public function setPreis($preis) {
        $this->preis = $preis;
    }

    public function getTyp() {
        return $this->typ;
    }

    public function setTyp($typ) {
        $this->typ = $typ;
    }

    public function getVerkaufstyp() {
        return $this->verkaufstyp;
    }

    public function setVerkaufstyp($verkaufstyp) {
        $this->verkaufstyp = $verkaufstyp;
    }

    public function getBild() {
        return $this->bild;
    }

    public function setBild($bild) {
        $this->bild = $bild;
    }

    public function getBeschreibung() {
        return $this->beschreibung;
    }

    public function setBeschreibung($beschreibung) {
        $this->beschreibung = $beschreibung;
    }


}

?>
