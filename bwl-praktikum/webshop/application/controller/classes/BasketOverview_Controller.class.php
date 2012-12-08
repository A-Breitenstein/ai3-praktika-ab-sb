<?php
class BasketOverview_Controller implements Controller{

    public function showPage(Request $request)
    {
        // creating MainPage and modifing header information
        $page = MainPage::create();
        $page->getLayoutElem("header")->addHeaderElem('<link rel="stylesheet" type="text/css" href="'.Registry::$settings['config']['CSS_PATH'].'basketoverview_view_layout.css">');
        $page->getLayoutElem("header")->addHeaderElem(
        '<script type="text/javascript">
            function removeItems(itemID,oldCount){
                var count_field = document.getElementById("product_"+itemID+"_count");
                var currentCount = count_field.value;
                if(currentCount >= 0 && currentCount < oldCount){
                    if(confirm("Sie sind dabei "+(oldCount-currentCount)+" Artikel von diesem Artikel entfernen")){
                          window.location.href = "basketoverview?itemID="+itemID+"&count="+currentCount;
                    }else{

                    }
                }else{
                    if(currentCount == oldCount){
                        alert("Zum entfernen die neue Menge die Sie behalten wollen eintragen.")
                    }
                    count_field.value=oldCount;
                }
            }

            function bestellen(){
                var day = document.getElementById("day").value;
                var month = document.getElementById("month").value;
                var year = document.getElementById("year").value;
                var gesamtpreis = document.getElementById("gesamtpreis").innerHTML;
                if(confirm("Sie sind dabei ihre Bestellung im Wert von "+gesamtpreis+" abzuschicken, bestätigen Sie dies bitte!")){
                   window.location.href = "basketoverview?bestellen=1&day="+day+"&month="+month+"&year="+year;
                }
            }

        </script>');
        // deciding which content to build
        // this $request->get doesnt mean get, its the PHP $_GET which is wraped in Request class
        ErrorReporter::logVarExport(__CLASS__."::showPage(request) => request get export",$request->get);
        ErrorReporter::logVarExport(__CLASS__."::showPage(request) => request post export",$request->post);

        $basketItem = $request->get["itemID"];
        $count = $request->get["count"];
        if(isset($basketItem) && isset($count)){
            $basketItem = mysql_real_escape_string($basketItem);
            $count = mysql_real_escape_string($count);
            if($_SESSION["basket"][$basketItem] > $count){

                if(!($count <= 0)){
                    $_SESSION["basket"][$basketItem] = $count;
                }else{
                    $_SESSION["basket"] = array_diff_key($_SESSION["basket"],array($basketItem => $count));
                }
            }
        }

        $products = array();
        if(isset($_SESSION["basket"])){
            foreach ($_SESSION["basket"] as $productID => $count){
                array_push($products,TeileMapperImpl::make()->getTeileById($productID));
            }
        }

        $month = $request->get["month"];
        $day = $request->get["day"];
        $year = $request->get["year"];
        if(isset($request->get["bestellen"]) and isset($month)and isset($day) and isset($year)){


            StrukturMapperImpl::make()->getAlleStrukturen();
            //listeVonTeilen und ListeVonStrukturen wird in getAlleStrukuren erzeugt
            Stueckliste::bestimmeOberUndUnterteile(Stueckliste::$listeVonTeilen,Stueckliste::$listeVonStrukturen);
//          Stueckliste::stueckliste_test();

            $lieferdatum = AuftragsVerfolgung::getTimestamp($day,$month,$year);

            foreach ($products as $product){
                $teil = Stueckliste::getProdukt($product->getBezeichnung());
                AuftragsVerfolgung::teilHinzufuegen($teil,$_SESSION["basket"][$teil->id()],$lieferdatum);
            }
            $_SESSION["basket"] = array();
            $products = array();
        }
        // fetch view and apply data to view
        $page->setContentView(BasketOverview_View::create());
        $page->getLayoutElem("contentview")->applyData(array("basket" => $_SESSION['basket'],"products" => $products));


        // show view
        $page->showPage($page->layoutElementMap);
    }
}
?>