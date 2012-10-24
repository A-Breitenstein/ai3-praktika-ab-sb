<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 22.10.12
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */
class Default_View  implements View
{
   private $dataMap;
   public function applyDataToView(array $dataMap){

       $this->dataMap = $dataMap;

   }
    public function applyData(array $dataMap)
    {
        $this->dataMap = $dataMap;
    }

    public function show()
    {
       echo('Hallo Content');
    }

    public static function create()
    {
        return new Default_View();
    }
}

?>