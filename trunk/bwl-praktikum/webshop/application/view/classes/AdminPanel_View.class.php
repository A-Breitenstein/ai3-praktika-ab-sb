<?php
class AdminPanel_View implements View{
    private $dataMap;
    public function applyData(array $dataMap) {
        $this->dataMap = $dataMap;
    }
    public function applyDataToView(array $dataMap) {
        $this->dataMap = $dataMap;
    }
    public function show() {
    ?>
        <div id="adminpanel">

        </div>

    <?php
    }
    public static function create() {
        return new AdminPanel_View();
    }
}
?>