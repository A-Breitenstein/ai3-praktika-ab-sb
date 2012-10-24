<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abg667
 */
interface KundeMapper {
    
    /**
     * Gibt den Kunden mit der angegebenen Kundennummer zurück.
     *
     * @param $kundenNr
     */
    public function getUserById($kundenNr);
    
    /**
     * Gibt den Kunden anhand der eMail zurück.
     *
     * @param $email
     */
    public function getUserByEmail($email);
    
}

?>
