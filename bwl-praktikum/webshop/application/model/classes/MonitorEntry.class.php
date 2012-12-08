<?php
class MonitorEntry{
    public $ip;
    public $http_refer;
    public $isDaily;
    public $page;
    public $hits;
    public $timestamp;
    public static $dbh;
               /*
                *
                *  CREATE TABLE monitorentry
  (
     id    INT PRIMARY KEY auto_increment,
     hits     INT,
     daily   BOOLEAN,
     page    varchar(255),
     timestamp BIGINT
                * */

    public static function processPageRequest($pagename,$request){

    }
    public static function getDBH(){
        if(empty(self::$dbh)){
            self::$dbh =  DBManager::create();
             self::$dbh->connect();

        }
        return self::$dbh;
    }
    public static function updateEntry($identifier){#
        if(strlen($identifier) > 13 ){
            self::updateDailyEntry($identifier);
            self::updateHourlyEntry($identifier);
        }
    }
    public static function updateDailyEntry($identifier){
        $time = new DateTime();
        $time->setTime(0,0,0);
        $currentTime = $time->getTimestamp();
        $result = self::getDBH()->query("SELECT * FROM `monitorentry` WHERE `page`='$identifier' and `daily` = '1' and `timestamp` = '$currentTime'");
        if(mysql_num_rows($result) == 0){
            self::getDBH()->query("INSERT INTO monitorentry(`hits`,`daily`,`page`,`timestamp`) VALUES(".
            "'1','1','$identifier','".$currentTime."')");
        }elseif(mysql_num_rows($result)==1){
            $row = mysql_fetch_assoc($result);
            self::getDBH()->query("UPDATE `monitorentry` SET `hits` = '".($row["hits"]+1)."' WHERE `id`='".$row["id"]."'");
        }
    }


    public static function updateHourlyEntry($identifier){
        $time = new DateTime();
        $hour = $time->format("H");
        $time->setTime($hour,0,0);
        $currentTime = $time->getTimestamp();
        $result = self::getDBH()->query("SELECT * FROM `monitorentry` WHERE `page`='$identifier' and `daily` = '0' and `timestamp` = '$currentTime'");
        if(mysql_num_rows($result) == 0){
            self::getDBH()->query("INSERT INTO monitorentry(`hits`,`daily`,`page`,`timestamp`) VALUES(".
                "'1','0','$identifier','".$currentTime."')");
        }elseif(mysql_num_rows($result)==1){
            $row = mysql_fetch_assoc($result);
            self::getDBH()->query("UPDATE `monitorentry` SET `hits` = '".($row["hits"]+1)."' WHERE `id`='".$row["id"]."'");
        }
    }
    public static function showPageHits(){
        echo("<table border=\"1px solid black\">");
        echo("<tr><td>Seite</td><td>Hits</td><td>Datum</td><td>IstTaeglich</td></tr>");
        $result = self::getDBH()->query("SELECT * FROM `monitorentry` ORDER BY `timestamp` desc");
           while($row = mysql_fetch_assoc($result)){
               if($row["daily"] == 0){
                   $daily = "N";
                   $datetime = date("D, d M y H:i:s",$row["timestamp"]);
               }else{
                   $daily = "J";
                    $datetime = date("d F Y",$row["timestamp"]);
               }
               echo("<tr onmouseout='javascript:unhighlight_row(this);' onmouseover='javascript:highlight_row(this);'><td>".$row["page"]."</td><td>".$row["hits"]."</td><td>".$datetime."</td><td>".$daily."</td></tr>");
           }
        echo("</table>");
    }

}
?>