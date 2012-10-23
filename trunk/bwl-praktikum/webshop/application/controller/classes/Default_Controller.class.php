<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 22.10.12
 * Time: 18:36
 * To change this template use File | Settings | File Templates.
 */
class Default_Controller extends Abstract_Controller
{
    public function showPage(Request $request){
        // fetch sql data

        // fetch view

        // apply data to view

        // show view
        $view = new Default_View();
        $view->applyDataToView(array("Name" => "Hans"));
    }
}
?>