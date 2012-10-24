<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 24.10.12
 * Time: 09:54
 * To change this template use File | Settings | File Templates.
 */
class Leftbar_MainPage implements Leftbar
{
      private $dataMap;
    public function show()
    {   echo('
        <div id="menu_entry1"> Produkte A</div>
        <div id="menu_entry2"> Produkte B</div>
        <div id="menu_entry3"> Produkte C</div>
    ');
    }

    public static function create()
    {
        return new Leftbar_MainPage();
    }
    public function applyData(array $dataMap)
    {
        $this->dataMap = $dataMap;
    }
}

?>
