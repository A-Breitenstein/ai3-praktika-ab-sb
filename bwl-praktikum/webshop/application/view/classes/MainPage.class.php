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


    ?><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
    <html>
        <head>
            <?php $LayoutElemMap['header']->printHeader(); ?>

        </head>
        <body>
            <div id="MainPage" >
                 <div id="TopBar">
                     <?php $LayoutElemMap['topbar']->show(); ?>
                 </div>
                <div id="LeftBar">
                    <?php $LayoutElemMap['leftbar']->show(); ?>
                </div>
                <div id="ContentView">
                    <?php $LayoutElemMap['contentview']->show(); ?>
                </div>
                <div id="Footer">
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

        $this->layoutElementMap["header"]->addHeaderElem('<link rel="stylesheet" type="text/css" href="'.Registry::$settings['config']['CSS_PATH'].'main_page_layout.css">');
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
