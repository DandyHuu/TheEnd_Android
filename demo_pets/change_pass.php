<?php

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$pass = $_POST['passnew'];
$userid = $_POST['userid'];



if ( $pass != null && $pass != "" ){

    $query = "UPDATE `user` SET`password`= '$pass' WHERE username = '$userid' ";

        if ( mysqli_query($conn, $query) ){

            header('HTTP/1.1 200 Register Success');
            echo $pass;
            echo $userid;

        } 
        else {
         
           header('HTTP/1.1 401 Register Fail');
        }

} 

?>