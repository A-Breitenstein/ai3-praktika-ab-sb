<?php
class AdminPanel_Controller implements Controller{

    public function showPage(Request $request)
    {
        // creating MainPage and modifing header information
        $page = MainPage::create();
        $page->getLayoutElem("header")->addHeaderElem('<link rel="stylesheet" type="text/css" href="'.Registry::$settings['config']['CSS_PATH'].'adminpanel_view_layout.css">');
        $page->getLayoutElem("header")->addHeaderElem('
        <script type="text/javascript">
               var old_color = "white";
               function highlight_row($divElem){
                    if(old_color == "white") old_color = $divElem.style.backgroundColor;
                    $divElem.style.backgroundColor = "#FFF2D5";
               }

               function unhighlight_row($divElem){
                    $divElem.style.backgroundColor = old_color;
               }

               function stueckListeVonAnfordern(){
                    var produkt = document.getElementById("stueckliste_produkt").value;
                    window.location.href = "adminpanel?stuecklisteVon="+produkt;
               }

       </script>');
        // deciding which content to build
        // this $request->get doesnt mean get, its the PHP $_GET which is wraped in Request class
        ErrorReporter::logVarExport(__CLASS__."::showPage(request) => request get export",$request->get);
        ErrorReporter::logVarExport(__CLASS__."::showPage(request) => request post export",$request->post);

        $stuecklisteProdukt = $request->get["stuecklisteVon"];

        //testdaten initialisieren
        Stueckliste::stueckliste_test();
        if(isset($stuecklisteProdukt))
            $stuecklisteProdukt = Stueckliste::getProdukt($stuecklisteProdukt);
        $abc_analyse_result = ABC_Analyse::abc_analyse_test();

//        ABC_Analyse::abc_analyse_test();
//        PrimaerbedarfsAnalyse::primaerbedarfstest();
        // fetch view and apply data to view
        $page->setContentView(AdminPanel_View::create());
        $page->getLayoutElem("contentview")->applyData(array(
                        "stueckliste_produkt" => $stuecklisteProdukt,
                        "abc_analyse_array" => $abc_analyse_result)
        );


        // show view
        $page->showPage($page->layoutElementMap);
    }
}
?>