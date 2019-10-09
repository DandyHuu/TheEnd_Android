<?php 

require_once 'connect.php';

$username       = $_POST['username'];
$comment      = $_POST['comment'];
$idbaiviet      = $_POST['idbaiviet'];

if ( $username != null && $comment != null  ){
	$date = date("d-m-Y");

    $query = "INSERT INTO `comment`(`id`, `id_baiviet`, `id_user`, `comment`, `date`) VALUES (null,'$idbaiviet','$username','$comment','$date') ";

        if ( mysqli_query($conn, $query) ){

            header('HTTP/1.1 200 Register Success');

        } 
        else {
         
           header('HTTP/1.1 401 Register Fail');
        }
}

?>