<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 24.10.12
 * Time: 08:49
 * To change this template use File | Settings | File Templates.
 */
interface Page
{
    //public function showPage(Header $header,Topbar $topbar, Leftbar $leftbar, Footer $footer);
    public function showPage($LayoutElemMap);
    public function setContentView(View $contentView);
    public function getLayoutElem($name);
    public function getLayoutElemNames();
    public static function create();
}

?>
