<?php

class DBManager{
  public $dbh;  //Variable for storing connection
  
  public function connect_with_params($set_host, $set_username, $set_password, $set_database){
    $this->dbh = mysql_connect($set_host, $set_username, $set_password,true)or die("cannot connect"); //Store data connection specifier in object
    mysql_select_db($set_database)or die("cannot select DB");
  }
  
  public function connect(){
    $this->dbh = mysql_connect($__DATABASE['hostname'], $__DATABASE['user'], $__DATABASE['pass'],true)or die("cannot connect"); //Store data connection specifier in object
    mysql_select_db($__DATABASE['dbname'])or die("cannot select DB");
    return true;

      }

    /**
     * @param $sql  - hallo wtf
     * @return mixed
     */
 public function query($sql)
 {
      $result =  mysql_query($sql,$this->dbh) OR die("<pre>\n".$sql."</pre>\n".mysql_error());  //Specify connection handler when doing query

       return  $result;
  }

  public function fetch($sql)
  {
       return mysql_fetch_array($this->query($sql));
  }
  public function fetch_assoc($sql){
        return mysql_fetch_assoc($this->query($sql));
  }
  public function fetch_array($sql){
        return mysql_fetch_array($this->query($sql));
  }
  public function close(){
    return mysql_close($this->dbh);
  }
    
  public static function create(){
    $classname = __CLASS__;
    return new $classname();
  }
}

