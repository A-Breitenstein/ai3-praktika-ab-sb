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
        // creating MainPage and modifing header information
        $page = MainPage::create();
        $page->getLayoutElem("header")->addHeaderElem('<script type="text/javascript">
                 var edi_feld_focused = false;
                 document.onkeydown = function(e){
                     var evt = e ? e:event;
                     var keyCode = evt.keyCode;
                     if(keyCode == 13 && edi_feld_focused){
                         search();
                     }
                 };
            function search(){
                //alert(document.getElementById("search_string").value);
                 var url ="products?search="+document.getElementById("search_string").value;
                 window.location.href = url;
            }

        </script>');
        // deciding which content to build
        // this $request->get doesnt mean get, its the PHP $_GET which is wraped in Request class
        ErrorReporter::logVarExport(__CLASS__."::showPage(request) => request get export",$request->get);
        ErrorReporter::logVarExport(__CLASS__."::showPage(request) => request post export",$request->post);
        $search_string = $request->get["search"];
        if(!empty($search_string)){
            // fetch sql data
            $resultSet = TeileMapperImpl::make()->getListOfTeileByBezeichung(mysql_real_escape_string($search_string));
        }else{
            // fetch sql data
            $resultSet = TeileMapperImpl::make()->getAlleProdukte();

        }



        // fetch view and apply data to view
        $page->setContentView(Products_View::create());
        $page->getLayoutElem("contentview")->applyData(array("products" => $resultSet));


        // show view
        $page->showPage($page->layoutElementMap);
    }
}

?>
