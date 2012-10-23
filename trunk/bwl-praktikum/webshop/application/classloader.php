<?php
class ClassLoader{
    public static $class_paths;
    public static function setClassPaths($paths){
        self::$class_paths = $paths;
    }
    public static function autoload($className){

        foreach(self::$class_paths as $key => $classPath){
            $filePath = $classPath . $className .".class.php";
            if(file_exists($filePath)){
                include_once($filePath);
                ErrorReporter::logMessage("ClassLoader::autoload(".$className.") => ".$classPath.$className.".class.php loaded");
                return ;
            }
        }
        ErrorReporter::logMessage("ClassLoader::autoload(".$className.") => ".$className." not found");
        die('Couldn�t find class: <strong>' . $className . '</strong> !');
            
    }
}
?>