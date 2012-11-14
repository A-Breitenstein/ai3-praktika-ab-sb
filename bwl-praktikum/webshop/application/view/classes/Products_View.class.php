<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
class Products_View implements View{
    private $dataMap;
public function applyData(array $dataMap) {
    $this->dataMap = $dataMap;
    }
public function applyDataToView(array $dataMap) {
     $this->dataMap = $dataMap;   
    }
public function show() {
  echo('
    <div style="margin-top: 5%;width: 100%">
   <div style="margin-bottom: 5%;margin-left:10%;margin-right:10%width: 80%; ">

            <div style="float:left; width: 50%;"><input type="text" id="search_string" onfocus="javascript:edi_feld_focused=true;"onblur="javascript:edi_feld_focused=false;" value="Suchbegriff" style="width: 100%;" ></div>
            <div onclick="javascript:search()" style="float:left;width:15%;border: 1px solid black;"> Suchen </div>
   </div>');

              foreach ($this->dataMap['products'] as $product){
                echo('<div style="float:left;height: 25%;width: 80%;margin-left: 10%;margin-right: 10%;border: 1px solid black;padding-top:4px;">
                      <div style="float: left;height:100%;width: 20%;margin:2%"> <img width=80%  height=80% src="'.$product->getBild().'" ></div>
                      <div style="float: left;height: 25%;width: 25%;">Bezeichnung: '.$product->getBezeichnung().'</div>
                      <div style="float: left;height: 25%;width: 25%;">Typ: '.$product->getTyp().'</div>
                      <div style="float: left;height: 25%;width: 25%;">Teile-Nr.'.$product->getTeileNr().'</div>
                      <div style="float: left;height: 75%;width: 65%;">Preis: '.$product->getPreis().'<br>Beschreibung:'.$product->getBeschreibung().'</div>
                      <div style="float: left;height: 75%;width: 10%;" onclick="location.href=\'products?'.$this->dataMap['prev_search'].'itemID='.$product->getTeileNr().'\'" >  <img width=60% height=50% src="'.Registry::$settings['config']["IMG_PATH"].'empty_basket.gif"> </div>
            </div>');
              }
           echo('</div>');


    }
public static function create() {
        return new Products_View();
    }
}
?>
