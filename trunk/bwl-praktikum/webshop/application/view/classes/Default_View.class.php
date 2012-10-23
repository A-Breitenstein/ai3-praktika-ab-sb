<?php
/**
 * Created by IntelliJ IDEA.
 * User: Sven
 * Date: 22.10.12
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */
class Default_View  extends Abstract_View
{
   public function applyDataToView(array $dataMap){
/*
   echo("<html>
   <body>
       hallo default view
   </body>
</html>");
*/
       $dataMap["name"] = "Sven";
       include(APP_PATH . "view/phtml/topbar_tmpl.phtml");
       include(APP_PATH . "view/phtml/leftbar_tmpl.phtml");
       echo('<div id="content" style="float: left;width: 75%;height:85%;background-color: #deb887;">');
       include(APP_PATH . "view/phtml/default_view.phtml");
       echo('</div>');
   }


}

?>