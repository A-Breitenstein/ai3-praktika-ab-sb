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
  ?>
   <div>
    <table>
        <tr>
            <td rowspan="3">Edifelt</td>
            <td rowspan="3">suchknpog</td>
        </tr>
        <tr>
          <?php 
              foreach ($dataMap['products'] as $product){
                  echo('<td>'.$product->getTeileNr().'</td>');
                  echo('<td>'.$product->getBezeichnung().'</td>');
                  echo('<td>'.$product->getPreis().'</td>');
                  echo('<td>'.$product->getTyp().'</td>');
                  echo('<td>'.$product->getBild().'</td>');
                  echo('<td>'.$product->getBeschreibung().'</td>');
              }
          ?>
        </tr>
    </table>
   </div> 
<?php
    }
public static function create() {
        return new Products_View();
    }
}
?>
