<?php 
header("Content-Type: application/json; charset=UTF-8");
require_once 'connect.php';

$key = $_POST['key'];

$des        = $_POST['des'];
$name       = $_POST['name'];
$species    = $_POST['species'];
$breed      = $_POST['breed'];
$gender     = $_POST['gender'];
$birth      = $_POST['birth'];
$picture    = $_POST['picture'];

if ( $key == "insert" ){

    $birth_newformat = date('Y-m-d', strtotime($birth));

    $query = "INSERT INTO pet (des,name,species,breed,gender,birth)
    VALUES ('$des','$name', '$species', '$breed', '$gender', '$birth_newformat') ";

        if ( mysqli_query($conn, $query) ){

            $last_id = mysqli_insert_id($conn);
            $result["last_id"] = $last_id;

            if ($picture == null) {

                $finalPath = "/demo_pets/pet_logo.png"; 
                $result["value"] = "1";
                $result["message"] = "Success";
    
                echo json_encode($result);
                mysqli_close($conn);

            } else {

                

                $id = mysqli_insert_id($conn);
                $path = "pets_picture/$id.jpeg";
                $finalPath = "/demo_pets/".$path;

                $insert_picture = "UPDATE pet SET picture='$finalPath' WHERE id='$id' ";
            
                if (mysqli_query($conn, $insert_picture)) {
            
                    if ( file_put_contents( $path, base64_decode($picture) ) ) {
                        
                        $result["value"] = "1";
                        $result["message"] = "Success!";
            
                        echo json_encode($result);
                        mysqli_close($conn);
            
                    } else {
                        
                        $response["value"] = "0";
                        $response["message"] = "Error! ".mysqli_error($conn);
                        echo json_encode($response);

                        mysqli_close($conn);
                    }

                }
            }

        } 
        else {
            $response["value"] = "0";
            $response["message"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
        }
}

?>