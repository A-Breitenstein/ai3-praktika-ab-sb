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
   <div>
    <table border="1" style="width: 75%;margin: 10%">
        <tr>
            <td colspan="3" ><input type="text" id="search_string" onfocus="javascript:edi_feld_focused=true;"onblur="javascript:edi_feld_focused=false;" value="Suchbegriff" style="width: 100%;" ></td>
            <td colspan="3" onclick="javascript:search()">suchknpog </td>
        </tr>
        <tr>
          ');
              foreach ($this->dataMap['products'] as $product){
                  echo('<td>'.$product->getTeileNr().'</td>');
                  echo('<td>'.$product->getBezeichung().'</td>');
                  echo('<td>'.$product->getPreis().'</td>');
                  echo('<td>'.$product->getTyp().'</td>');
                  echo('<td>'.$product->getBild().'</td>');
                  echo('<td>'.$product->getBeschreibung().'</td>');
              }

       echo(' </tr>
    </table>
   </div> ');

    }
public static function create() {
        return new Products_View();
    }
}
?>
