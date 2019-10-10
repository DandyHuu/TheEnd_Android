<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$query = "SELECT * FROM user";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'username'        =>$row['username'], 
        'password'      =>$row['password'], 
        'email'   =>$row['email'],
        'phone'     =>$row['phone'],
        'address'    =>$row['address'],
        'roles'     =>$row['roles'],
        'avatar'   =>"http://$server_name" . $row['avatar'])
    );
}

echo json_encode($response);

mysqli_close($conn);

?>