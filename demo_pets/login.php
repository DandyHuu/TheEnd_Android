<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';
$USER_NAME = $_POST['username'];
$USER_PASS = $_POST['password'];

if ($USER_NAME != null && $USER_PASS != null) {
    $query = "SELECT * FROM `user` WHERE username = '$USER_NAME' AND password = '$USER_PASS'";
    $result = mysqli_query($conn, $query);
    $response = array();

    $server_name = $_SERVER['SERVER_ADDR'];

    while( $row = mysqli_fetch_assoc($result) ){

        array_push($response, 
        array(
            'username'        =>$row['username'], 
            'password'      =>$row['password'], 
            'fullname'      =>$row['fullname'], 
            'email'   =>$row['email'],
            'birth'   =>$row['birth'],
            'face'   =>$row['face'],
            'phone'     =>$row['phone'],
            'address'    =>$row['address'],
            'roles'     =>$row['roles'],
            'avatar'   =>"http://$server_name" . $row['avatar'])
        );
    }

    echo json_encode($response);

    mysqli_close($conn);
} else {
    $response["value"] = "0";
    $response["message"] = "Error! ".mysqli_error($conn);
    echo "sai!";
    echo json_encode($response);

    mysqli_close($conn);
}

?>