<?php
class AdminPanel_Controller implements Controller{

    public function showPage(Request $request)
    {
        // creating MainPage and modifing header information
        $page = MainPage::create();
        $page->getLayoutElem("header")->addHeaderElem('<link rel="stylesheet" type="text/css" href="'.Registry::$settings['config']['CSS_PATH'].'adminpanel_view_layout.css">');
        $page->getLayoutElem("header")->addHeaderElem('
        <script type="text/javascript">
               var old_color = "white";
               function highlight_row($divElem){
                    if(old_color == "white") old_color = $divElem.style.backgroundColor;
                    $divElem.style.backgroundColor = "#FFF2D5";
               }

               function unhighlight_row($divElem){
                    $divElem.style.backgroundColor = old_color;
               }

               function stueckListeVonAnfordern(){
                    var produkt = document.getElementById("stueckliste_produkt").value;
                    window.location.href = "adminpanel?stuecklisteVon="+produkt;
               }

               function teil_hinzufuegen(){
                        var bezeichnung,preis,kategorie,wirdAngeboten,bild,beschreibung;
                        bezeichnung = document.getElementById("teil_bezeichnung").value;
                        preis = document.getElementById("teil_preis").value;
                        kategorie = document.getElementById("teil_typ").value;
                        wirdAngeboten = document.getElementById("teil_angeboten").value;
                        bild = document.getElementById("teil_bild").value;
                        beschreibung = document.getElementById("teil_beschreibung").value;
                        window.location.href = "adminpanel?teil_bezeichnung="+bezeichnung+
                                               "&teil_preis="+preis+
                                               "&teil_typ="+kategorie+
                                               "&teil_angeboten="+wirdAngeboten+
                                               "&teil_bild="+bild+
                                               "&teil_beschreibung="+beschreibung;
               }

               /*
               *          <input type="text" id="struktur_otnr" value="OTNR">
                        <input type="text" id="struktur_utnr" value="UTNR">
                        <input type="text" id="struktur_menge" value="Menge">
                        <button onclick="javascript:struktur_hinzufuegen()">Eintragen</button>*/
               function struktur_hinzufuegen(){
                     var otnr,utnr,menge;
                        otnr = document.getElementById("struktur_otnr").value;
                        utnr = document.getElementById("struktur_utnr").value;
                        menge = document.getElementById("struktur_menge").value;

                         window.location.href = "adminpanel?struktur_otnr="+otnr+
                                               "&struktur_utnr="+utnr+
                                               "&struktur_menge="+menge;

               }

               function berechnen(){
                    var summe = 0.0;
                    for(var i = 0; i<4;i++){
                        summe = summe + parseFloat(document.getElementById("monat"+i).value);
                    }
                    var durchschnitt = summe/4.0;

                    var glaettungsfaktor = parseFloat(document.getElementById("glaettungsfaktor").value);
                    var letzterMonat = parseFloat(document.getElementById("monat0").value);
//                    $vorletzteBedarfsmenge + ($glaettungsfaktor*($letzteBedarfsmenge-$vorletzteBedarfsmenge));
                    var ergebnis = durchschnitt + (glaettungsfaktor* ( letzterMonat - durchschnitt ));
                    document.getElementById("ergebnis").value = ergebnis;

               }

               function kunde_hinzufuegen(){
//                                       <input type="text" id="kunde_Name" value="Name">
//                        <input type="text" id="kunde_Vorname" value="Vorname">
//                        <input type="text" id="kunde_eMail" value="eMail">
                    var name,vorname,email;
                    name = document.getElementById("kunde_Name").value;
                    vorname = document.getElementById("kunde_Vorname").value;
                    email = document.getElementById("kunde_eMail").value;

                    window.location.href = "adminpanel?kunde_Name="+name+
                                               "&kunde_Vorname="+vorname+
                                               "&kunde_eMail="+email;

               }

       </script>');
        // deciding which content to build
        // this $request->get doesnt mean get, its the PHP $_GET which is wraped in Request class
        ErrorReporter::logVarExport(__CLASS__."::showPage(request) => request get export",$request->get);
        ErrorReporter::logVarExport(__CLASS__."::showPage(request) => request post export",$request->post);

        $stuecklisteProdukt = $request->get["stuecklisteVon"];
          $teil_bezeichnung=   $request->get["teil_bezeichnung"];
          $teil_preis=$request->get["teil_preis"];
          $teil_typ=$request->get["teil_typ"];
          $teil_angeboten=$request->get["teil_angeboten"];
          $teil_bild=$request->get["teil_bild"];
          $teil_beschreibung=$request->get["teil_beschreibung"];

        $struktur_otnr = $request->get["struktur_otnr"];
        $struktur_utnr = $request->get["struktur_utnr"];
        $struktur_menge = $request->get["struktur_menge"];

        $kunde_name = $request->get["kunde_Name"];
        $kunde_vorname = $request->get["kunde_Vorname"];
        $kunde_email = $request->get["kunde_eMail"];

        if(isset($teil_bezeichnung) && isset($teil_preis) && isset($teil_typ) && isset($teil_angeboten) && isset($teil_beschreibung))
            TeileMapperImpl::make()->teilErstellen($teil_bezeichnung,$teil_preis,$teil_typ,$teil_angeboten,"application/view/images/".$teil_bild,$teil_beschreibung);

        if(isset($struktur_otnr) && isset($struktur_utnr) && isset($struktur_menge))
            StrukturMapperImpl::make()->strukturAnlegen($struktur_otnr,$struktur_utnr,$struktur_menge);

        if(isset($kunde_name) && isset($kunde_vorname) && isset($kunde_email)){
            KundeMapperImpl::make()->kundeHinzufuegen($kunde_name,$kunde_vorname,$kunde_email);
        }

        //testdaten initialisieren
        if(isset($stuecklisteProdukt)){

            StrukturMapperImpl::make()->getAlleStrukturen();
            //listeVonTeilen und ListeVonStrukturen wird in getAlleStrukuren erzeugt
            Stueckliste::bestimmeOberUndUnterteile(Stueckliste::$listeVonTeilen,Stueckliste::$listeVonStrukturen);
//          Stueckliste::stueckliste_test();
            $stuecklisteProdukt = Stueckliste::getProdukt($stuecklisteProdukt);
        }
//        $abc_analyse_result = ABC_Analyse::abc_analyse_test();
        $abc_analyse_result = ABC_Analyse::analyse(ABC_Analyse::convertToABC_ADT(TeileMapperImpl::make()->getAlleProdukte()));

//        PrimaerbedarfsAnalyse::primaerbedarfstest();

        //var_dump(BestellungMapperImpl::make()->getBestellungenByKundenID(1));

        // fetch view and apply data to view
        $page->setContentView(AdminPanel_View::create());
        $page->getLayoutElem("contentview")->applyData(array(
                        "stueckliste_produkt" => $stuecklisteProdukt,
                        "abc_analyse_array" => $abc_analyse_result)
        );


        // show view
        $page->showPage($page->layoutElementMap);
    }
}
?>