<?php 

require_once 'connect.php';

$key = $_POST['key'];

$id_user        = $_POST['id_user'];
$id_pet         = $_POST['id_pet'];  
$title          = $_POST['title'];
$description    = $_POST['description'];

if ( $key == "insert" ){
    $timeup = date("d-m-Y");
    $query = "INSERT INTO baiviet (id_user,id_pet,title,description,timeup)
    VALUES ('$id_user', $id_pet, '$title', '$description', '$timeup') ";

        if ( mysqli_query($conn, $query) ){

            header('HTTP/1.1 200 Addpost Success');
             $response["value"] = "1";
             $response["message"] = "Success! ";
             echo json_encode($result);
             mysqli_close($conn);

        } 
        else {
            $response["value"] = "0";
            $response["message"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
        }
}

?>