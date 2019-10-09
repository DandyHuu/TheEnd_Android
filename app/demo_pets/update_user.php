<?php

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$username = $_POST['username'];
$password = $_POST['password'];
$fullname = $_POST['fullname'];
$email = $_POST['email'];
$phone = $_POST['phone'];
$address = $_POST['address'];
$face = $_POST['face'];
$birth = $_POST['birth'];



if ( $username != null && $username != "" ){

    $query = "UPDATE user SET fullname='$fullname',email='$email',phone='$phone',address='$address',face='$face',birth='$birth' WHERE username = '$username'";

        if ( mysqli_query($conn, $query) ){

            header('HTTP/1.1 200 Register Success');

        } 
        else {
         
           header('HTTP/1.1 401 Register Fail');
        }

} 

?>