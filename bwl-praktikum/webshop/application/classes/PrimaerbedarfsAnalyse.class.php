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
    public static function primaerbedarfstest(){
        $bedarfeVormonate = array(50,50,50,90);
        echo(PrimaerbedarfsAnalyse::exponentielleGlaettung(0.2,90,
            PrimaerbedarfsAnalyse::arithmetischesMittel($bedarfeVormonate)));
    }
}
?>