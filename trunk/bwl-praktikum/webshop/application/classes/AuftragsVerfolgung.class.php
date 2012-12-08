<?php
class AuftragsVerfolgung{

    /*
     * date("d.m.y",time());
     * */
    private static $query_bedarfHinzufuegen = "INSERT INTO Bedarf(`TeilNr`,`Datum`,`Menge`) VALUES('%s','%s','%s')";
    private static $query_getBedarfByDatumAndTeilNr = "SELECT * FROM `Bedarf` WHERE `TeilNr` = '%s' AND `Datum` = '%s'";
    private static $query_updateBedarf = "UPDATE `Bedarf` SET `Menge` = '%s' WHERE `ID` = '%s'";

    private static $query_auftragHinzufuegen = "INSERT INTO Auftrag(`TeilNr`,`Datum`,`Menge`) VALUES('%s','%s','%s')";
    private static $query_getAuftragByDatumAndTeilNr = "SELECT * FROM `Auftrag` WHERE `TeilNr` = '%s' AND `Datum` = '%s'";
    private static $query_updateAuftrag = "UPDATE `Auftrag` SET `Menge` = '%s' WHERE `ID` = '%s'";

    private static $query_bedarfsdeckungHinzufuegen = "INSERT INTO Bedarfsdeckung(`TeilNr`,`BedarfsDatum`,`AuftragsDatum`,`Menge`) VALUES ('%s','%s','%s','%s')";
    private static $query_getBedarfsdeckungByTeilNrAndAuftragsDatum = "SELECT * FROM `Bedarfsdeckung` WHERE `TeilNr` = '%s' and `AuftragsDatum` = '%s'";
    private static $query_updateBedarfsdeckung = "UPDATE `Bedarfsdeckung` SET `Menge` = '%s' WHERE `ID` = '%s'";

    private static $query_bedarfsableitungHinzufuegen = "INSERT INTO Bedarfsableitung(`OTNr`,`UTNr`,`AuftragsDatum`,`BedarfsDatum`,`MengeUT`) VALUES('%s','%s','%s','%s','%s')";
    private static $query_getBedarfsableitungByOTUT = "SELECT * FROM `Bedarfsableitung` WHERE `OTNr` = '%s' and `UTNr` = '%s' and `AuftragsDatum` = '%s' and `BedarfsDatum` = '%s'";
    private static $query_updateBedarfsableitung = "UPDATE `Bedarfsableitung` SET `MengeUT` = '%s' WHERE `ID` = '%s'";

    private static $query_alleBedarfeAnzeigen = "SELECT b.`ID`,t.`Bezeichnung` ,b.`Datum`,b.`Menge` FROM `Bedarf` b,`Teile` t WHERE  b.`TeilNr` = t.`TNr`";
    private static $query_alleBedarfeByBezeichnung = "SELECT b.`ID`,t.`Bezeichnung` ,b.`Datum`,b.`Menge` FROM `Bedarf` b,`Teile` t WHERE  b.`TeilNr` = t.`TNr` AND t.`Bezeichnung` = '%s'";
    private static $query_alleBedarfeByDatum = "SELECT b.`ID`,t.`Bezeichnung` ,b.`Datum`,b.`Menge` FROM `Bedarf` b,`Teile` t WHERE  b.`TeilNr` = t.`TNr` AND b.`Datum` = '%s'";
    private static $query_alleBedarfeBetweenDatumAndBezeichnung = "SELECT b.`ID`,t.`Bezeichnung` ,b.`Datum`,b.`Menge` FROM `Bedarf` b,`Teile` t WHERE  b.`TeilNr` = t.`TNr` AND t.`Bezeichnung` = '%s' AND b.`Datum` >= '%s' AND b.`Datum` <= '%s' ";

    private static $query_alleBedarfsableitungenAnzeigenOT = "SELECT * FROM `Bedarfsableitung` ba,`Teile` t WHERE ba.`OTNr` = t.`TNr` ORDER BY ba.`BedarfsDatum` desc ";
    private static $query_alleBedarfsableitungenAnzeigenUT = "SELECT * FROM `Bedarfsableitung` ba,`Teile` t WHERE ba.`UTNr` = t.`TNr` ORDER BY ba.`BedarfsDatum` desc";

    private static function day_in_sec(){
        return 24*60*60;
    }
    private static $dbh;
    private static function getDbh(){
        if(empty(self::$dbh)){
            self::$dbh = DBManager::create();
            self::$dbh->connect();
        }
        return self::$dbh;
    }

    public static function createBedarf($ID,$teilID,$datum,$menge){
        return new BedarfImpl($ID,$teilID,$datum,$menge);
    }
    public static function createAuftrag($ID,$teilID,$datum,$menge){
        return new AuftragImpl($ID,$teilID,$datum,$menge);
    }
    public static function createBedarfsdeckung($ID,$teilID,$bedarfsDatum,$auftragsDatum,$menge){
        return new BedarfsdeckungImpl($ID,$teilID,$bedarfsDatum,$auftragsDatum,$menge);
    }
    public static function createBedarfsableitung($ID,$OTNr,$UTNr,$bedarfsDatum,$auftragsDatum,$UTmenge){
        return new BedarfsableitungImpl($ID,$OTNr,$UTNr,$bedarfsDatum,$auftragsDatum,$UTmenge);
    }



    private static function getBedarfByTeilNrAndDatum($teilNr,$datum){
        $sql = sprintf(self::$query_getBedarfByDatumAndTeilNr,$teilNr,$datum);
        $result = self::getDbh()->query($sql);
        $row_count = mysql_num_rows($result);
        if( $row_count == 1){
            $row = mysql_fetch_assoc($result);
            return self::createBedarf($row["ID"],$row["TeilNr"],$row["Datum"],$row["Menge"]);
        }elseif($row_count == 0){
            return null;
        }else{
            die("AuftragsVerfolgung::getBedarfByTeilNrAndDatum:: there are more then one rows with given parameters");
        }

    }

    private static function bedarfHinzufuegen(Bedarf $bedarf){

        $result = self::getBedarfByTeilNrAndDatum($bedarf->getTeilID(),$bedarf->getDatum());
        if(!empty($result)){
            $sql = sprintf(self::$query_updateBedarf,$result->getMenge()+$bedarf->getMenge(),$result->getID());
            self::getDbh()->query($sql);
        }else{
            $sql = sprintf(self::$query_bedarfHinzufuegen,$bedarf->getTeilID(),$bedarf->getDatum(),$bedarf->getMenge());
            self::getDbh()->query($sql);
        }


    }


    private static function getAuftragByTeilNrAndDatum($teilNr,$datum){
        $sql = sprintf(self::$query_getAuftragByDatumAndTeilNr,$teilNr,$datum);
        $result = self::getDbh()->query($sql);
        $row_count = mysql_num_rows($result);
        if( $row_count == 1){
            $row = mysql_fetch_assoc($result);
            return self::createAuftrag($row["ID"],$row["TeilNr"],$row["Datum"],$row["Menge"]);
        }elseif($row_count == 0){
            return null;
        }else{
            die("AuftragsVerfolgung::getAuftragByTeilNrAndDatum:: there are more then one rows with given parameters");
        }

    }

    private static function auftragHinzufuegen(Auftrag $auftrag){

        $result = self::getAuftragByTeilNrAndDatum($auftrag->getTeilID(),$auftrag->getDatum());
        if(!empty($result)){
            $sql = sprintf(self::$query_updateAuftrag,$result->getMenge()+$auftrag->getMenge(),$result->getID());
            self::getDbh()->query($sql);
        }else{
            $sql = sprintf(self::$query_auftragHinzufuegen,$auftrag->getTeilID(),$auftrag->getDatum(),$auftrag->getMenge());
            self::getDbh()->query($sql);
        }

    }

    private static function getBedarfsdeckungByTeilAndAuftragsDatum($teilNr,$auftragsDatum){
        $sql = sprintf(self::$query_getBedarfsdeckungByTeilNrAndAuftragsDatum,$teilNr,$auftragsDatum);
        $result = self::getDbh()->query($sql);
        $row_count = mysql_num_rows($result);
        if( $row_count == 1){
            $row = mysql_fetch_assoc($result);
            return self::createBedarfsdeckung($row["ID"],$row["TeilNr"],$row["BedarfsDatum"],$row["AuftragsDatum"],$row["Menge"]);
        }elseif($row_count == 0){
            return null;
        }else{
            die("AuftragsVerfolgung::getBedarfByTeilNrAndDatum:: there are more then one rows with given parameters");
        }
    }
    private static function bedarfsdeckungHinzufuegen(Bedarfsdeckung $bedarfsdeckung){

        $result = self::getBedarfsdeckungByTeilAndAuftragsDatum($bedarfsdeckung->getTeilID(),$bedarfsdeckung->getAuftragsDatum());
        if(!empty($result)){
            $sql = sprintf(self::$query_updateBedarfsdeckung,$result->getMenge()+$bedarfsdeckung->getMenge(),$result->getID());
            self::getDbh()->query($sql);
        }else{                                                                                  /*`TeilNr`,`BedarfsDatum`,`AuftragsDatum`,`Menge`*/
            $sql = sprintf(self::$query_bedarfsdeckungHinzufuegen,$bedarfsdeckung->getTeilID(),$bedarfsdeckung->getBedarfsDatum(),$bedarfsdeckung->getAuftragsDatum(),$bedarfsdeckung->getMenge());
            self::getDbh()->query($sql);
        }

    }

    private static function getBedarfsableitungByOTUTAndDate($OTNr,$UTNr,$AuftragsDatum,$BedarfsDatum){
        $sql = sprintf(self::$query_getBedarfsableitungByOTUT,$OTNr,$UTNr,$AuftragsDatum,$BedarfsDatum);
        $result = self::getDbh()->query($sql);
        $row_count = mysql_num_rows($result);
        if( $row_count == 1){
            $row = mysql_fetch_assoc($result);
            return self::createBedarfsableitung($row["ID"],$row["OTNr"],$row["UTNr"],$row["BedarfsDatum"],$row["AuftragsDatum"],$row["MengeUT"]);
        }elseif($row_count == 0){
            return null;
        }else{
            die("AuftragsVerfolgung::getBedarfsableitungByOTUTAndDate:: there are more then one rows with given parameters");
        }

    }

    private static function bedarfsableitungHinzufuegen(Bedarfsableitung $bedarfsableitung){
        $result = self::getBedarfsableitungByOTUTAndDate($bedarfsableitung->getOTNr(),
                  $bedarfsableitung->getUTNr(),$bedarfsableitung->getAuftragsDatum(),
                  $bedarfsableitung->getBedarfsDatum());
        if(!empty($result)){
            $sql = sprintf(self::$query_updateBedarfsableitung,$result->getMengeUT()+$bedarfsableitung->getMengeUT(),$result->getID());
            self::getDbh()->query($sql);
        }else{
            $sql = sprintf(self::$query_bedarfsableitungHinzufuegen,$bedarfsableitung->getOTNr(),$bedarfsableitung->getUTNr(),
                           $bedarfsableitung->getAuftragsDatum(),$bedarfsableitung->getBedarfsDatum(),$bedarfsableitung->getMengeUT());
            self::getDbh()->query($sql);
        }
    }

    public static function getTimestamp($day,$month,$year){
        $dateobj = new DateTime();
        $dateobj->setDate($year,$month,$day);
        $dateobj->setTime(0,0,0);
        return $dateobj->getTimestamp();
    }
    /*     var_dump($dateobj->diff($dateobj2)); */
    public  static function getDateString($timestamp){
        $dateobj = new DateTime();
        $dateobj->setTimestamp($timestamp);
        return $dateobj->format("d.m.Y");
    }



    public static  function  test_bedarfsDaten(){
        $aircar1 = self::createBedarf(0,8,self::getTimestamp(5,12,2012),100);
        $aircar2 = self::createBedarf(0,8,self::getTimestamp(5,12,2012),100);
        self::bedarfHinzufuegen($aircar1);
        self::bedarfHinzufuegen($aircar2);
        $aircar_db_entry = self::getBedarfByTeilNrAndDatum(8,self::getTimestamp(5,12,2012));
        if($aircar_db_entry->getMenge() == 200 and $aircar_db_entry->getDatum() == self::getTimestamp(5,12,2012)){

        }else{
            var_dump($aircar_db_entry);
            die("bedarfsDaten test failed...");
        }

    }
    public static function test_auftragsDaten(){
        $aircar1 = self::createAuftrag(0,8,self::getTimestamp(4,12,2012),100);
        $aircar2 = self::createAuftrag(0,8,self::getTimestamp(4,12,2012),100);
        self::auftragHinzufuegen($aircar1);
        self::auftragHinzufuegen($aircar2);
        $aircar_db_entry = self::getAuftragByTeilNrAndDatum(8,self::getTimestamp(4,12,2012));
        if($aircar_db_entry->getMenge() == 200 and $aircar_db_entry->getDatum() == self::getTimestamp(4,12,2012)){
            var_dump($aircar_db_entry);
            echo("auftragsDaten test sucssesfull");
        }else{
            var_dump($aircar_db_entry);
            die("auftragsDaten test failed...");
        }
    }
    public static function test_bedarfsdeckungsDaten(){
        $aircar = self::createBedarfsdeckung(0,8,self::getTimestamp(4,12,2012),self::getTimestamp(4,12,2012),200);
        self::bedarfsdeckungHinzufuegen($aircar);
    }

    public static function test_bedarfsableitungsDaten(){
        $rumpf = self::createBedarfsableitung(0,8,9,self::getTimestamp(3,12,2012),self::getTimestamp(4,12,2012),200);
        $motor = self::createBedarfsableitung(0,8,10,self::getTimestamp(3,12,2012),self::getTimestamp(4,12,2012),400);
        self::bedarfsableitungHinzufuegen($rumpf);
        self::bedarfsableitungHinzufuegen($motor);
    }

    public static function teilHinzufuegen(Teil $teil,$menge,$fertigstellungsdatum){
        Stueckliste::mengenErmitteln($teil,$menge);

        // teil selbst eintragen
        self::bedarfHinzufuegen(self::createBedarf(0,$teil->id(),$fertigstellungsdatum,$menge));
        self::auftragHinzufuegen(self::createAuftrag(0,$teil->id(),
            $fertigstellungsdatum,
            $menge));
        self::bedarfsdeckungHinzufuegen(self::createBedarfsdeckung(
            0,$teil->id(),$fertigstellungsdatum,$fertigstellungsdatum,$menge
        ));

        // unterteile eintragen
        self::_createJobs($teil,$fertigstellungsdatum);


    }

    private static function _createJobs(Teil $teil,$fertigstellungsdatum){
        if(count($teil->getIstOberteilInStruktur())> 0){
            foreach($teil->getIstOberteilInStruktur() as $struktur){
                $eintagVorher = $fertigstellungsdatum-self::day_in_sec();
                self::bedarfHinzufuegen(self::createBedarf(0,$struktur->unterteil()->id(),
                        $eintagVorher,
                        $struktur->menge()));

                self::auftragHinzufuegen(self::createAuftrag(0,$struktur->unterteil()->id(),
                    $eintagVorher,
                    $struktur->menge()));

                self::bedarfsableitungHinzufuegen(
                    self::createBedarfsableitung(0,$struktur->oberteil()->id(),
                                    $struktur->unterteil()->id(),$eintagVorher,$fertigstellungsdatum,$struktur->menge())
                );

                self::bedarfsdeckungHinzufuegen(self::createBedarfsdeckung(
                    0,$struktur->unterteil()->id(),$eintagVorher,$eintagVorher,$struktur->menge()
                ));

                self::_createJobs($struktur->unterteil(),$eintagVorher);
            }
        }
    }


    public static function getAlleBedarfe(){
       $result = self::getDbh()->query(self::$query_alleBedarfeAnzeigen);
       return self::fetchRowsToArray($result);
    }
    private static function fetchRowsToArray($result){
        $result_array = array();
        if(mysql_num_rows($result)>0){
            while($row = mysql_fetch_assoc($result)){
                array_push($result_array,$row);
            }
        }
        return $result_array;
    }
    public static function printBedarfTabelle($listeVonBedarf){
?>
        <table border="1px solid black">
            <tr>
                <th>ID</th>
                <th>Teil</th>
                <th>Datum</th>
                <th>Menge</th>
            </tr>

            <?php
                foreach($listeVonBedarf as $row){
                   echo("<tr onmouseout='javascript:unhighlight_row(this);' onmouseover='javascript:highlight_row(this);'>
                        <td>".$row["ID"]."</td>
                        <td class=\"clickable\" onclick=\"location.href='products?search=".$row["Bezeichnung"]."'\">".$row["Bezeichnung"]."</td>
                        <td>".self::getDateString($row["Datum"])."</td>
                        <td>".$row["Menge"]."</td>
                   </tr>");
                }
            ?>
        </table>
<?php
    }

    public static function getAlleBedarfsAbleitungen(){
      $resultOT = self::getDbh()->query(self::$query_alleBedarfsableitungenAnzeigenOT);
      $resultUT = self::getDbh()->query(self::$query_alleBedarfsableitungenAnzeigenUT);
      return self::fetchBedarfsAbleitungsRows($resultOT,$resultUT);
    }
    private static function fetchBedarfsAbleitungsRows($resultOT,$resultUT){
        $result_array = array();
        if (mysql_num_rows($resultOT) > 0) {
            while ($rowOT = mysql_fetch_assoc($resultOT)) {
                $rowUT = mysql_fetch_assoc($resultUT);
                $rowOT["BezeichnungOT"] = $rowOT["Bezeichnung"];
                $rowOT["BezeichnungUT"] = $rowUT["Bezeichnung"];
                array_push($result_array,$rowOT);
            }
        }
        return $result_array;
    }
    public static function printBedarfAbleitungsTabelle($listeVonBedarfableitungen){
        ?>
        <table border="1px solid black">
            <tr>
                <th>Oberteil</th>
                <th>Unterteil</th>
                <th>BedarfsDatum</th>
                <th>AuftragsDatum</th>
                <th>MengeUT</th>
            </tr>

            <?php
            foreach($listeVonBedarfableitungen as $row){
                echo("<tr onmouseout='javascript:unhighlight_row(this);' onmouseover='javascript:highlight_row(this);'>
                        <td class=\"clickable\" onclick=\"location.href='products?search=".$row["BezeichnungOT"]."'\">".$row["BezeichnungOT"]."</td>
                        <td class=\"clickable\" onclick=\"location.href='products?search=".$row["BezeichnungUT"]."'\">".$row["BezeichnungUT"]."</td>
                        <td>".self::getDateString($row["BedarfsDatum"])."</td>
                        <td>".self::getDateString($row["AuftragsDatum"])."</td>
                        <td>".$row["MengeUT"]."</td>
                   </tr>");
            }
            ?>
        </table>
    <?php
    }


    public static function getBedarfeVonTeil($bezeichnung){
        $sql = sprintf(self::$query_alleBedarfeByBezeichnung,$bezeichnung);
        $rows = self::fetchRowsToArray(self::getDbh()->query($sql));
        $verbrauch = 0;
        if(!empty($rows)){
            foreach($rows as $bedarfs_row){
                $verbrauch+=$bedarfs_row["Menge"];
            }
        }
        return $verbrauch;
    }

    public static function getBedarfeVonTeilInMonatJahr($bezeichnung,$timestamp){
        $date = new DateTime();
        $date->setTimestamp($timestamp);
        $date->setTime(0,0,0);
        $month = intval($date->format("m"));
        $year = intval($date->format("Y"));
        $lowerbound = self::getTimestamp(0,$month,$year);
        $upperbound = self::getTimestamp(30,$month,$year);
        $sql = sprintf(self::$query_alleBedarfeBetweenDatumAndBezeichnung,$bezeichnung,$lowerbound,$upperbound);
        $rows = self::fetchRowsToArray(self::getDbh()->query($sql));
        $verbrauch = 0;
        if(!empty($rows)){
            foreach($rows as $bedarfs_row){
                $verbrauch+=$bedarfs_row["Menge"];
            }
        }
        return $verbrauch;
    }

}

interface Bedarf{
    function getID();
    function getTeilID();
    function getDatum();
    function getMenge();


}

interface Auftrag{
    function getID();
    function getTeilID();
    function getDatum();
    function getMenge();

}

interface Bedarfsdeckung{
    function getID();
    function getTeilID();
    function getBedarfsDatum();
    function getAuftragsDatum();
    function getMenge();

}

interface Bedarfsableitung{
    function getID();
    function getOTNr();
    function getUTNr();
    function getAuftragsDatum();
    function getBedarfsDatum();
    function getMengeUT();



}

class BedarfImpl implements Bedarf{
    private $id;
    private $teilID;
    private $datum;
    private $menge;

    public function __construct($id,$teilID,$datum,$menge){
        $this->id = $id;
        $this->teilID = $teilID;
        $this->datum = $datum;
        $this->menge = $menge;
    }
    function getID()
    {
        return $this->id;
    }

    function getTeilID()
    {
        return $this->teilID;
    }

    function getDatum()
    {
        return $this->datum;
    }

    function getMenge()
    {
        return $this->menge;
    }
}
class AuftragImpl implements Auftrag{
    private $id;
    private $teilID;
    private $datum;
    private $menge;

    public function __construct($id,$teilID,$datum,$menge){
        $this->id = $id;
        $this->teilID = $teilID;
        $this->datum = $datum;
        $this->menge = $menge;
    }
    function getID()
    {
        return $this->id;
    }

    function getTeilID()
    {
        return $this->teilID;
    }

    function getDatum()
    {
        return $this->datum;
    }

    function getMenge()
    {
        return $this->menge;
    }
}


class BedarfsdeckungImpl implements Bedarfsdeckung{
    private $id;
    private $teilID;
    private $bedarfsDatum;
    private $auftragsDatum;
    private $menge;

    public function __construct($id,$teilID,$bedarfsDatum,$auftragsDatum,$menge){
        $this->id = $id;
        $this->teilID = $teilID;
        $this->bedarfsDatum = $bedarfsDatum;
        $this->auftragsDatum = $auftragsDatum;
        $this->menge = $menge;
    }
    function getID()
    {
        return $this->id;
    }

    function getTeilID()
    {
        return $this->teilID;
    }

    function getBedarfsDatum()
    {
        return $this->bedarfsDatum;
    }

    function getAuftragsDatum()
    {
        return $this->auftragsDatum;
    }

    function getMenge()
    {
        return $this->menge;
    }
}

class BedarfsableitungImpl implements Bedarfsableitung{
    private $id;
    private $otnr;
    private $utnr;
    private $auftragsDatum;
    private $bedarfsDatum;
    private $mengeut;

    public function __construct($id,$otnr,$utnr,$bedarfsDatum,$auftragsDatum,$mengeUT){
        $this->id = $id;
        $this->otnr = $otnr;
        $this->utnr = $utnr;
        $this->bedarfsDatum = $bedarfsDatum;
        $this->auftragsDatum = $auftragsDatum;
        $this->mengeut = $mengeUT;
    }
    function getID()
    {
        return $this->id;
    }

    function getOTNr()
    {
        return $this->otnr;
    }

    function getUTNr()
    {
        return $this->utnr;
    }

    function getAuftragsDatum()
    {
        return $this->auftragsDatum;
    }

    function getBedarfsDatum()
    {
        return $this->bedarfsDatum;
    }

    function getMengeUT()
    {
        return $this->mengeut;
    }
}


?>