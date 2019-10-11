<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';
$login_name = $_POST['login_name'];
if ($login_name != null) {
    # code...
    $query = "SELECT n.id, n.cate_noti, n.mess, n.user_id, n.baiviet_id, n.dateup, u.fullname FROM noti n JOIN user u ON n.user_id = u.username WHERE n.user_bi_like = '$login_name' ORDER BY n.id";
    $result = mysqli_query($conn, $query);
    $response = array();

    $server_name = "http://192.168.82.102:88";

    while( $row = mysqli_fetch_assoc($result) ){

        array_push($response, 
        array(
            'id'        =>$row['id'], 
            'cate_noti'        =>$row['cate_noti'], 
            'mess'      =>$row['mess'], 
            'user_id'   =>$row['user_id'],
            'dateup'   =>$row['dateup'],
            'fullname'   =>$row['fullname'],
            'baiviet_id'     =>$row['baiviet_id']
            ) 
        );
    }

    echo json_encode($response);

    mysqli_close($conn);

}

?>