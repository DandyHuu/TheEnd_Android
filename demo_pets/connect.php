<?php 
define('host', 'localhost');
define('user', 'root');
define('pass', '');
define('db', 'pets');

$conn = mysqli_connect(host, user, pass, db) or die('Unable to Connect');
mysqli_set_charset($conn,"utf8");

?>