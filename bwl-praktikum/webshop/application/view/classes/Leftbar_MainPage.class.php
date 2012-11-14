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
        <div id="menu_entry1"> <a href="products">Produkte</a></div>
        <div id="menu_entry2"> <a href="products?search=Jaguar">Jaguar</a></div>
        <div id="menu_entry3"> <a href="products?search=BMW">BMW</a></div>
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
