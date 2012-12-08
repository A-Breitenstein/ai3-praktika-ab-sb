<?php
class PrimaerbedarfsAnalyse {
    public static function exponentielleGlaettung($glaettungsfaktor,$letzteBedarfsmenge,$vorletzteBedarfsmenge){
        return $vorletzteBedarfsmenge + ($glaettungsfaktor*($letzteBedarfsmenge-$vorletzteBedarfsmenge));
    }
    public static function arithmetischesMittel($listeVonAbsatzmengenEinesProduktesProQuartal){
        if(!is_array($listeVonAbsatzmengenEinesProduktesProQuartal))die("PrimaerbedarfsAnalyse::arithmetischesMittel:: given argument isnt an array");
        $result = 0;
        $count = 0;
        foreach($listeVonAbsatzmengenEinesProduktesProQuartal as $absatzMengeProQuartal){
            $result = $result + $absatzMengeProQuartal;
            $count++;
        }
        return $result/$count;
    }

    public static function calcZukuenftigenBedarf($teilname,$monat,$jahr,$faktor){
        $bedarfeProMonat = array();
        $newTimestamp = AuftragsVerfolgung::getTimestamp(15,$monat,$jahr);
        for($i = 0;$i<4; $i++){
            array_push($bedarfeProMonat,AuftragsVerfolgung::getBedarfeVonTeilInMonatJahr($teilname,$newTimestamp));
            $newTimestamp-=(24*60*60*30);

        }
        return PrimaerbedarfsAnalyse::exponentielleGlaettung($faktor,$bedarfeProMonat[0],
            PrimaerbedarfsAnalyse::arithmetischesMittel($bedarfeProMonat));
    }
    public static function primaerbedarfstest(){
        echo("Dezember 2012:: ".AuftragsVerfolgung::getBedarfeVonTeilInMonatJahr("Aircar",AuftragsVerfolgung::getTimestamp(5,12,2012))."<br>");
        $bedarfeVormonate = array(50,50,50,90);
        echo(PrimaerbedarfsAnalyse::exponentielleGlaettung(0.2,90,
            PrimaerbedarfsAnalyse::arithmetischesMittel($bedarfeVormonate)));
    }
}
?>