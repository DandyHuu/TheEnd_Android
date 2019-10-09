<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';
$id = $_POST['idbaiviet'];
if ($id != null & $id != "") {
    $query = "SELECT c.id, c.id_baiviet, c.id_user, c.comment, c.date, u.fullname, u.avatar FROM comment c JOIN user u ON c.id_user = u.username WHERE c.id_baiviet = $id ORDER BY c.id ";
    $result = mysqli_query($conn, $query);
    $response = array();

    $server_name = "http://192.168.82.102:88";

    while( $row = mysqli_fetch_assoc($result) ){

        array_push($response, 
        array(
            'id'        =>$row['id'], 
            'id_baiviet'        =>$row['id_baiviet'], 
            'fullname'      =>$row['fullname'], 
            'avatar'   =>$server_name.$row['avatar'],
            'comment'     =>$row['comment'],
            'date'     =>$row['date'],
            'id_user'    =>$row['id_user']
            ) 
        );
    }

    echo json_encode($response);

    mysqli_close($conn);
}


?>