<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';
$id_baiviet = $_POST['id_baiviet'];
if ($id_baiviet != null) {

    $query = "SELECT b.id, b.id_user, b.id_pet, b.title, b.description, b.timeup,u.username, u.password, u.fullname, u.email, u.phone, u.address, u.roles, u.avatar, u.face, u.birth FROM baiviet b JOIN user u ON u.username = b.id_user WHERE b.id = '$id_baiviet' ORDER BY u.username";
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
}
?>