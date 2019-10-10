<?php

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$username = $_POST['username'];
$avatar = $_POST['avatar'];



if ( $username != null && $username != "" ){

    $query = "UPDATE user SET avatar='$avatar' WHERE username = '$username'";

        if ( mysqli_query($conn, $query) ){

            header('HTTP/1.1 200 Register Success');

        } 
        else {
         
           header('HTTP/1.1 401 Register Fail');
        }

} 

?>