<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 23.10.12
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
abstract class Abstract_Controller
{
    public function showPage(Request $request){
        die(__FUNCTION__ . "in ".__CLASS__. "not implemented!");
    }
    public function dodo(){}
}
?>