<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 24.10.12
 * Time: 09:14
 * To change this template use File | Settings | File Templates.
 */
class MainPage implements Page
{
    public $layoutElementMap;
    public function showPage($LayoutElemMap){


    ?>
    <html>
        <head>
            <?php $LayoutElemMap['header']->printHeader(); ?>

        </head>
        <body style="margin: 0px;">
            <div id="MainPage" >
                 <div id="TopBar" style="width: 100%;height:15%;background-color: gray;">
                     <?php $LayoutElemMap['topbar']->show(); ?>
                 </div>
                <div id="LeftBar" style="float:left;width: 25%;height:80%;background-color: #7fffd4;">
                    <?php $LayoutElemMap['leftbar']->show(); ?>
                </div>
                <div id="ContentView" style="float:left; overflow-y:auto;width: 75%;height: 80%;background-color: #deb887;">
                    <?php $LayoutElemMap['contentview']->show(); ?>
                </div>
                <div id="Footer" style="float:left;width: 100%;height: 5%;background-color: yellow">
                    <?php $LayoutElemMap['footer']->show(); ?>
                </div>

            </div>
        </body>
    </html>
    <?php
    }

    public function __construct(){
        $this->layoutElementMap = array(
            "topbar" =>Topbar_MainPage::create(),
            "leftbar" =>Leftbar_MainPage::create(),
            "footer" => Footer_MainPage::create(),
            "contentview" => Default_View::create(),
            "header" => HeaderImpl::create()
        );
    }
    public static function create()
    {
       return new MainPage();

    }

    public function getLayoutElem($name)
    {
        return $this->layoutElementMap[$name];
    }

    public function getLayoutElemNames()
    {
        return array_keys($this->layoutElementMap);
    }

    public function setContentView(View $contentView)
    {
        $this->layoutElementMap['contentview'] = $contentView;

    }
}

?>
