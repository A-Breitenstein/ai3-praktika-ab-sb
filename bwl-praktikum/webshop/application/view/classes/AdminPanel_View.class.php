<?php
class AdminPanel_View implements View{
    private $dataMap;
    public function applyData(array $dataMap) {
        $this->dataMap = $dataMap;
    }
    public function applyDataToView(array $dataMap) {
        $this->dataMap = $dataMap;
    }
    public function show() {
    ?>
        <div id="adminpanel">
              <div id="stueckliste">
                  <div style='float: left;'>
                      Erstelle Stueckliste von <input id='stueckliste_produkt' type='text' value='<?php if(isset($this->dataMap["stueckliste_produkt"])) echo($this->dataMap["stueckliste_produkt"]->name());else echo("Produkt1");?>'>
                  </div>
                  <div style='float:left;cursor: pointer;' onclick='javascript:stueckListeVonAnfordern();'>
                       Erstellen
                  </div>
                  <?php
                  if(!empty($this->dataMap["stueckliste_produkt"]))
                        Stueckliste::printTeil($this->dataMap["stueckliste_produkt"]);
                  ?></div>
              <div id="abc_analyse">
                  ABC Analyse
                  <?php
                      echo("<div class='abc_row'><div class='abc_col'> ProduktID </div> <div class='abc_col'> ProduktName </div><div class='abc_col'> Preis </div><div class='abc_col'> Kategorie</div></div>");
                  foreach($this->dataMap["abc_analyse_array"] as $abc_adt){
                      echo("<div class='abc_row' onmouseout='javascript:unhighlight_row(this);' onmouseover='javascript:highlight_row(this);'>
                                <div class='abc_col'>".$abc_adt->productID() ."</div>"
                                ."<div class='abc_col' style='cursor:pointer;' onclick=\"location.href='products?search=".$abc_adt->productName()."'\">".$abc_adt->productName()."</div>"
                                ."<div class='abc_col'>".number_format($abc_adt->wholePrice(),2,',','.')."</div>"
                                ."<div class='abc_col'>".$abc_adt->categorie()."</div>
                           </div>"
                      );
                  }
//            echo("Name: ".$abc_adt->productName() ." :: Kategorie: ".$abc_adt->categorie()." :: Preis: ".number_format($abc_adt->wholePrice(),2,',','.')."<br>");

                  ?></div>
              <div id="bedarfs_analyse">

                  <div>
                      <div> Verbrauch in den Letzten 3 Monaten sowie </div>
                      <div>Monat n-3<input value="50"></div>
                      <div>Monat n-2<input value="50"></div>
                      <div>Monat n-1<input value="50"></div>
                      <div>Monat n <input value="90"></div>

                  </div>
              </div>
        </div>

    <?php
    }
    public static function create() {
        return new AdminPanel_View();
    }
}
?>