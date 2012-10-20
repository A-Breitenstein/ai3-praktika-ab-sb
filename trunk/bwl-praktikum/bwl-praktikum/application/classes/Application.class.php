<?php
class Application{
    
    private function __construct(){
        $this->main();
    }
    public static function start(){
        return new Application();
    }
    public function main(){
        $this->route();
    }
    public function route(){
        echo $_SERVER['REQUEST_URI'];
    }
}

?>