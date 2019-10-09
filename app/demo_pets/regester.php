<?php 

require_once 'connect.php';

$username       = $_POST['username'];
$password    = $_POST['password'];
$phone      = $_POST['phone'];
$email     = $_POST['email'];

if ( $username != null && $password != null && $phone != null && $email != null  ){


    $query = "INSERT INTO `user`(`username`, `password`, `email`, `phone`) VALUES ('$username','$password','$email','$phone') ";

        if ( mysqli_query($conn, $query) ){

            header('HTTP/1.1 200 Register Success');

        } 
        else {
         
           header('HTTP/1.1 401 Register Fail');
        }
}

?>