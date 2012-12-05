<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Kunde
 *
 * @author abg667
 */
class Kunde {
    private $kundenNr;
    private $name;
    private $vorname;
    private $email;
    private $erstellDatum;

   public function __construct($kundenNr, $name, $vorname, $email, $erstellDatum){
       $this->kundenNr = $kundenNr;
       $this->name = $name;
       $this->vorname = $vorname;
       $this->email = $email;
       $this->erstellDatum = $erstellDatum;
   }
    public static function create($kundenNr, $name, $vorname, $email, $erstellDatum){
        return new Kunde($kundenNr, $name, $vorname, $email, $erstellDatum);
    }


    // Getter & Setter
    public function getKundenNr() {
        return $this->kundenNr;
    }

    public function setKundenNr($KundenNr) {
        $this->kundenNr = $KundenNr;
    }

    public function getName() {
        return $this->name;
    }

    public function setName($Name) {
        $this->name = $Name;
    }

    public function getVorname() {
        return $this->vorname;
    }

    public function setVorname($Vorname) {
        $this->vorname = $Vorname;
    }

    public function getEmail() {
        return $this->email;
    }

    public function setEmail($Email) {
        $this->email = $Email;
    }

    public function getErstellDatum() {
        return $this->erstellDatum;
    }

    public function setErstellDatum($ErstellDatum) {
        $this->erstellDatum = $ErstellDatum;
    }




}

?>
