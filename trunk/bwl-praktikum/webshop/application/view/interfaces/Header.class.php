<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 24.10.12
 * Time: 09:27
 * To change this template use File | Settings | File Templates.
 */
interface Header{
    public function printHeader();
    public function addHeaderElem($string);
    public static function create();
}
?>
