<?php 
class Registry{
    
    public static $objects = array();
    public static $controller = array();
    
    public static $settings= array("config" => array(), 
                                   "classpaths" => array(),
                                   "database" => array()
                                    );
     public static function registerController(){
         $controllerPathPostFix = "_Controller.class.php";
         $length = strlen($controllerPathPostFix);
         $exclude_list = array(".","..","example.txt");
         $filesInDir = array_diff(scandir(self::$settings["classpaths"]["controller_classes"]),$exclude_list);
         foreach($filesInDir as $filename){

            self::$controller[strtolower(substr($filename,0,-$length))] = substr($filename,0,-strlen(".class.php"));
         }

         ErrorReporter::logVarExport('Registry::$controller => controllers registered',self::$controller);

     }
}
?>