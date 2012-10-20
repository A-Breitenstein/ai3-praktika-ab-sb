<?php
class ErrorReporter{
    public static $reporting = true;
    public static function logPageRequest(){
        if(self::$reporting){
            $logdatei=fopen(BASE_PATH."logs/logfile.txt","a");
           
             
            if(!isset($_SERVER['HTTP_REFERER'])){
                $http_reffer = "direkter zugriff!";
            }
            else{
                $http_reffer = $_SERVER['HTTP_REFERER'];
            }   
            fputs($logdatei,
                date("d.m.Y, H:i:s",time()) .
                ", " . $_SERVER['REMOTE_ADDR'] .
                ", " . $_SERVER['REQUEST_METHOD'] .
                ", " . $_SERVER['PHP_SELF'] .
                ", " . $_SERVER['HTTP_USER_AGENT'] .
                ", " . $http_reffer .
                ", ".  $_SERVER['QUERY_STRING']."\n"
                );
            fclose($logdatei);
        }
    }
    public static function logMessage($message){
       if(self::$reporting){
           $logdatei=fopen(BASE_PATH."logs/consolelog.txt","a");
            fputs($logdatei,
                date("d.m.Y, H:i:s :",time()) .$message ."\n"
                );
            fclose($logdatei);
        }
        
    }
    
    public static function logVarExport($message,$var){
       if(self::$reporting){
           $logdatei=fopen(BASE_PATH."logs/consolelog.txt","a");
            $tmp = var_export($var,true);
            fputs($logdatei,
                date("d.m.Y, H:i:s : ",time()).$message."\n" .$tmp."\n"
                );
            fclose($logdatei);
        }
    }
    
}
?>