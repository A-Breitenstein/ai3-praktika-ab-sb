<?php
class AdminPanel_Controller implements Controller{

    public function showPage(Request $request)
    {
        // creating MainPage and modifing header information
        $page = MainPage::create();
        $page->getLayoutElem("header")->addHeaderElem('<link rel="stylesheet" type="text/css" href="'.Registry::$settings['config']['CSS_PATH'].'adminpanel_view_layout.css">');

        // deciding which content to build
        // this $request->get doesnt mean get, its the PHP $_GET which is wraped in Request class
        ErrorReporter::logVarExport(__CLASS__."::showPage(request) => request get export",$request->get);
        ErrorReporter::logVarExport(__CLASS__."::showPage(request) => request post export",$request->post);
        $search_string = $request->get["search"];
        $basketItem = $request->get["itemID"];

        ABC_Analyse::abc_analyse_test();
        echo("-------------------------------------------<br>");
        Stueckliste::stueckliste_test();
        echo("-------------------------------------------<br>");
        PrimaerbedarfsAnalyse::primaerbedarfstest();
        // fetch view and apply data to view
        $page->setContentView(AdminPanel_View::create());
        $page->getLayoutElem("contentview")->applyData(array());


        // show view
        $page->showPage($page->layoutElementMap);
    }
}
?>