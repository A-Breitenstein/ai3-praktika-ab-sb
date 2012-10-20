<?php 
/* bootstrap */

/* Error Reporting: */
error_reporting(E_ALL & ~E_NOTICE);

define("BASE_PATH",dirname(realpath(__FILE__))."\\");
define("APP_PATH",BASE_PATH . "application\\");

require_once(APP_PATH . "config.php");
define("PATH",$_CONFIG['path']);

require_once(APP_PATH . "registry.php");
require_once(APP_PATH . "classloader.php");
// adding class directorys
ClassLoader::setClassPaths($__CLASSPATHS);
// binding ClassLoader
spl_autoload_register(array('ClassLoader', 'autoload'));

// linking settings to registry
Registry::$settings['config'] = &$__CONFIG;
Registry::$settings['classpaths'] = &$__CLASSPATHS;
Registry::$settings['database'] = &$__DATABASE;




ErrorReporter::logPageRequest();

//starting main Application
$app = Application::start();
?>