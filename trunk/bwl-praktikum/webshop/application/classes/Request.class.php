<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 22.10.12
 * Time: 18:40
 * To change this template use File | Settings | File Templates.
 */
class Request
{
    public $get, $post;

    public static function val($get, $post)
    {

        $obj = new Request();
        $obj->get = $get;
        $obj->post = $post;

        return $obj;
    }
}
