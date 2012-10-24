<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 24.10.12
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */
interface View extends LayoutElement
{
    public function applyDataToView(array $dataMap);

}

?>
