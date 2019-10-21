<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$query = "SELECT b.id, b.id_user, b.id_pet, b.title, b.description, b.timeup, p.name, p.species, p.breed, p.gender, p.birth, p.picture, p.love, p.des, u.fullname, u.avatar FROM baiviet b JOIN user u ON b.id_user = u.username JOIN pet p ON b.id_pet = p.id ORDER BY b.id";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = "http://192.168.1.87:88";

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'id'        =>$row['id'], 
        'id_user'        =>$row['id_user'], 
        'id_pet'        =>$row['id_pet'], 
        'title'        =>$row['title'], 
        'description'        =>$row['description'], 
        'timeup'        =>$row['timeup'],
        'fullname'        =>$row['fullname'],
        'avatar'        =>$server_name.$row['avatar'],

        'name'      =>$row['name'], 
        'species'   =>$row['species'],
        'breed'     =>$row['breed'],
        'gender'    =>$row['gender'],
        'birth'     =>$row['birth'],
        'picture'   =>$server_name . $row['picture'],
        'love'      =>$row['love'] ,
        'des'      =>$row['des']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>