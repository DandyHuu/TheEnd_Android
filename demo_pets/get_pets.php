<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$query = "SELECT * FROM pet ORDER BY id DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = "http://192.168.82.102:88";

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'id'        =>$row['id'], 
        'name'      =>$row['name'], 
        'species'   =>$row['species'],
        'breed'     =>$row['breed'],
        'gender'    =>$row['gender'],
        'birth'     =>$row['birth'],
        'picture'   =>$server_name . $row['picture'],
        'love'      =>$row['love'] ,
        'value'      =>$row['value'] ,
        'message'      =>$row['message']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>