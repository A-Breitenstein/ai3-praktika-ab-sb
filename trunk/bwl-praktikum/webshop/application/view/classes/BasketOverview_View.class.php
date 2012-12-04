<?php
class BasketOverview_View implements View{
    private $dataMap;
    public function applyData(array $dataMap) {
    $this->dataMap = $dataMap;
    }
    public function applyDataToView(array $dataMap) {
    $this->dataMap = $dataMap;
    }
    public function show() {
    ?>
        <div id="basketoverview">
            <div id="basket_table">
             <?php
                $whole_price = 0;
                $basket_entry =
            '<div class="basket_entry">
                <div class="basket_product_id">%s</div>
                <div class="basket_product_name">%s</div>
                <div class="basket_product_price">%s</div>
                <div class="basket_product_count"><input id="product_%s_count" type="text" size="4" value="%s"></div>
                <div class="basket_product_wholeprice">%s</div>
                <div class="basket_product_remove" onclick="javascript:removeItems(%s,%s);">%s</div>

            </div>';
                echo(sprintf($basket_entry,
                    "Artikel Nr.",
                    "Name",
                    "Preis",
                    0,"Menge",
                    "Gesamt Preis",
                    0,"Menge",
                    ""));
                if(isset($this->dataMap['products']))
                foreach ($this->dataMap['products'] as $product){
                    $itemID = $product->getTeileNr();
                    $price = $product->getPreis();
                    $count = $this->dataMap['basket'][$itemID];
                    $productName = $product->getBezeichnung();
                    $whole_price = ($price*$count)+$whole_price;
                    echo(sprintf($basket_entry,
                            $itemID,
                            "<a href=\"products?search=$productName\">$productName</a>",
                            number_format($price,2,',',''),
                            $itemID,$count,
                        number_format(($price*$count), 2, ',', ''),
                            $itemID,$count,
                            "Entfernen"));
                /*
                 *  <div id="product_name">Bezeichnung: '.$product->getBezeichnung().'</div>
                      <div id="product_typ">Typ: '.$product->getTyp().'</div>
                      <div id="product_id">Teile-Nr.'.$product->getTeileNr().'</div>
                      <div id="product_price">Preis: '.$product->getPreis().*/
            }
            ?>
            <div id="basket_whole_price">Gesamtpreis: <?php echo(number_format($whole_price, 2, ',', '')) ?></div>
            <div id="bestellen"> zur Kasse</div>

            </div>
        </div>
    <?php
    }
    public static function create() {
            return new BasketOverview_View();
        }
}
?>