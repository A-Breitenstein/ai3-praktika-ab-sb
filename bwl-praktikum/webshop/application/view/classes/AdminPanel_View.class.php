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
                      <div>Monat n-3<input id="monat3" value="50"></div>
                      <div>Monat n-2<input id="monat2" value="50"></div>
                      <div>Monat n-1<input id="monat1" value="50"></div>
                      <div>Monat n <input id="monat0" value="90"></div>
                      <div>Glaettungfaktor <input id="glaettungsfaktor" value="0.20"></div>
                      <button onclick="javascript:berechnen()">Berechnen</button>
                      <div>Ergebnis <input id="ergebnis"></div>

                  </div>
              </div>
              <div id="operationen">
                <div id ="einfuegen">
                    <div id="teil">
                        Teil erstellen
                        <input type="text" id="teil_bezeichnung" value="Produkt1">
                        <input type="text" id="teil_preis" value="Preis">
                        <input type="text" id="teil_typ" value="Kategorie">
                        <input type="text" id="teil_angeboten" value="wird es angeboten?">
                        <input type="text" id="teil_bild" value="bildurl">
                        <input type="text" id="teil_beschreibung" value="beschreibung" size="94">
                        <button onclick="javascript:teil_hinzufuegen()">Eintragen</button>
                    </div>
                    <div id="struktur">
                        Struktur erstellen
                        <input type="text" id="struktur_otnr" value="OTNR">
                        <input type="text" id="struktur_utnr" value="UTNR">
                        <input type="text" id="struktur_menge" value="Menge">
                        <button onclick="javascript:struktur_hinzufuegen()">Eintragen</button>
                    </div>
                    <div id="kunde">
                        Kunde erstellen
                        <input type="text" id="kunde_Name" value="Name">
                        <input type="text" id="kunde_Vorname" value="Vorname">
                        <input type="text" id="kunde_eMail" value="eMail">
                        <button onclick="javascript:kunde_hinzufuegen()">Eintragen</button>
                    </div>
                    <div id="bestellung">
                        Bestellung erstellen
                        <input type="text" id="bestellung_KundenNr" value="KundenNr">
                        <button onclick="javascript:bestllung_hinzufuegen()">Eintragen</button>
                        Artikel der Bestellung hinzufuegen
                        <input type="text" id="bestellung_BestellungsNr" value="BestellungsNr">
                        <input type="text" id="bestellung_TNr" value="TeileNr">

                        <button onclick="javascript:artikel_bestellung_hinzufuegen()">Eintragen</button>
                    </div>
                    <div id="pagehits">
                        Seiten Statistik
                         <?php MonitorEntry::showPageHits()?>
                    </div>

                    <div id="artikelhits">

                    </div>

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