<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 24.10.12
 * Time: 09:44
 * To change this template use File | Settings | File Templates.
 */
interface LayoutElement{
    public function show();
    public function applyData(array $dataMap);

    public static function create();
}
?>
