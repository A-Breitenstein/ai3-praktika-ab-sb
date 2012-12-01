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
        $page->getLayoutElem("header")->addHeaderElem('<link rel="stylesheet" type="text/css" href="'.Registry::$settings['config']['CSS_PATH'].'products_view_layout.css">');
        $page->getLayoutElem("header")->addHeaderElem('
        <script type="text/javascript">
                 var edi_feld_focused = false;
                 var basket = new Basket();
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
                function addToBasket(productid){
                 var oldCookie = document.cookie;
                 document.cookie = oldCookie+""

                }
                function Basket(){
                    this.items = {};

                }
                Basket.prototype.addItem = function(itemID){
                    if(this.items[itemID] == undefined){
                        this.items[itemID] = 1;

                    }else{
                       var tmp = this.items[itemID];
                       this.items[itemID] = tmp+1;

                    }
                };
                Basket.prototype.removeItem = function(itemID){
                    if(!this.items[itemID] == undefined){
                       var tmp = this.items[itemID];
                       this.items[itemID] = tmp-1;

                    }
                };
         </script>');
        // deciding which content to build
        // this $request->get doesnt mean get, its the PHP $_GET which is wraped in Request class
        ErrorReporter::logVarExport(__CLASS__."::showPage(request) => request get export",$request->get);
        ErrorReporter::logVarExport(__CLASS__."::showPage(request) => request post export",$request->post);
        $search_string = $request->get["search"];
        $basketItem = $request->get["itemID"];
        if(!empty($basketItem)){
            if(empty($_SESSION["basket"]))
                $_SESSION["basket"] = array();

            $itemID = mysql_real_escape_string($basketItem);
            $count = $_SESSION["basket"][$itemID];
            if(!empty($count)){
               $_SESSION["basket"][$itemID] = $count+1;
            }else{
               $_SESSION["basket"][$itemID] = 1;
            }
        }
        if(!empty($search_string)){
            // fetch sql data
            var_dump(mysql_real_escape_string($search_string));
            $resultSet = TeileMapperImpl::make()->getListOfTeileByBezeichnung(mysql_real_escape_string($search_string));
            $prev_search = "search=".$search_string."&";
        }else{
            // fetch sql data
            $resultSet = TeileMapperImpl::make()->getAlleProdukte();

        }



        // fetch view and apply data to view
        $page->setContentView(Products_View::create());
        $page->getLayoutElem("contentview")->applyData(array("products" => $resultSet,"prev_search"=>$prev_search));


        // show view
        $page->showPage($page->layoutElementMap);
    }
}

?>
