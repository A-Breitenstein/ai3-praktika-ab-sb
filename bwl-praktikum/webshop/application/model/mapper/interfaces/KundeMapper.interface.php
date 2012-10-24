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
    
    public static function getUserById($kundenNr);
    
    public static function getUserByEmail($email);
    
}

?>
