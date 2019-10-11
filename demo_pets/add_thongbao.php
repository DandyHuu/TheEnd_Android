<?php 
header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$cate_noti       = 1;
$mess      = $_POST['mess'];
$user_id      = $_POST['user_id'];
$baiviet_id      = $_POST['baivietid'];
$user_bi_like      = $_POST['user_bi_like'];

if ( $user_id != $user_bi_like && $user_bi_like != null  ){
	date_default_timezone_set('Asia/Bangkok');
	$date = date("d-m-Y h:i:sa");
    $query = "INSERT INTO noti (id, cate_noti, mess, user_id, baiviet_id, dateup, user_bi_like) VALUES (0,$cate_noti,'$mess','$user_id',$baiviet_id,'$date','$user_bi_like')";

        if ( mysqli_query($conn, $query) ){

            header('HTTP/1.1 200 Register Success');
            echo $query;

        } 
        else {
         
           header('HTTP/1.1 401 Register Fail');
        }
}

?>