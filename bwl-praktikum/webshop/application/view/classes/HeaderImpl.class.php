<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 24.10.12
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
class HeaderImpl implements Header{

    private $headerElemsArray = array();

    public function __construct(){
        array_push($this->headerElemsArray,'<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">');
    }
    public function printHeader()
    {
        // TODO: Implement printHeader() method.
        foreach($this->headerElemsArray as $elem){
            echo("$elem");
        }
    }

    public function addHeaderElem($string)
    {
        array_push($this->headerElemsArray,$string);
    }

    public static function create()
    {
        return new HeaderImpl();
    }
}
?>
