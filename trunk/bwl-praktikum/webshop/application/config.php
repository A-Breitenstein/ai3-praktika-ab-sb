<?php 
$__CONFIG = array();
$__CONFIG["path"] = "webshop";
$__CONFIG["default_controller"] = "welcome";

/* class paths */
$__CLASSPATHS = array();
array_push($__CLASSPATHS,
APP_PATH . "controller\classes\\",
APP_PATH . "model\classes\\",
APP_PATH . "view\classes\\",
APP_PATH . "classes\\"
);
$__CLASSPATHS["controller_classes"]  = APP_PATH . "controller\classes\\";
$__CLASSPATHS["model_classes"] = APP_PATH . "model\classes\\";
$__CLASSPATHS["view_classes"]  = APP_PATH . "view\classes\\";
$__CLASSPATHS["app_classes"]   = APP_PATH . "classes\\";

/* database infos */
$__DATABASE = array();
$__DATABASE['hostname'] = 'localhost';
$__DATABASE['user'] = 'root';
$__DATABASE['pass'] = '';
$__DATABASE['dbname'] ="bwl_praktikum";
?>