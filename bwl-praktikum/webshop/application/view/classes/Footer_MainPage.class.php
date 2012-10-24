<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 24.10.12
 * Time: 09:58
 * To change this template use File | Settings | File Templates.
 */
class Footer_MainPage implements Footer
{
    private $dataMap;
    public function show()
    {
        echo('
            <div id="Contact"> Kontakt</div>
            <div id="Help"> Hilfe </div>
        ');
    }

    public static function create()
    {
        return new Footer_MainPage();
    }

    public function applyData(array $dataMap)
    {
        $this->dataMap = $dataMap;
     }
}

?>
