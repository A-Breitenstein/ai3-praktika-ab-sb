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
    <div idproduct_content">
        <div id="product_search_field">
            <input type="text" id="search_string" onfocus="javascript:edi_feld_focused=true;" onblur="javascript:edi_feld_focused=false;" value="Suchbegriff">
            <div id="product_search_button" onclick="javascript:search()"> Suchen </div>
        </div>');

              foreach ($this->dataMap['products'] as $product){
                echo('<div id="product_entry">
                      <div id="product_img"> <img id="img" src="'.$product->getBild().'" ></div>
                      <div id="product_name">Bezeichnung: '.$product->getBezeichnung().'</div>
                      <div id="product_typ">Typ: '.$product->getTyp().'</div>
                      <div id="product_id">Teile-Nr.'.$product->getTeileNr().'</div>
                      <div id="product_price">Preis: '.$product->getPreis().'<br>Beschreibung:'.$product->getBeschreibung().'</div>
                      <div id="product_add_to_basket" onclick="location.href=\'products?'.$this->dataMap['prev_search'].'itemID='.$product->getTeileNr().'\'" >  <img width=60% height=50% src="'.Registry::$settings['config']["IMG_PATH"].'empty_basket.gif"> </div>
            </div>');
              }
           echo('</div>');


    }
public static function create() {
        return new Products_View();
    }
}
?>
