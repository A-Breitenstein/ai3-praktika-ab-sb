<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 24.10.12
 * Time: 11:45
 * To change this template use File | Settings | File Templates.
 */
class Products_Controller implements Controller
{

    public function showPage(Request $request)
    {
        // fetch sql data

        // fetch view

        // apply data to view

        // show view
        $page = MainPage::create();
        $page->getLayoutElem("topbar")->applyData(array("btn1" => "Button1","btn2" => "Button2","btn3"=>"Button3"));

        $page->showPage($page->layoutElementMap);
    }
}

?>
