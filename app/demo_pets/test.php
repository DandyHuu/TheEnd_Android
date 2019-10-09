<?php
include 'collection.php';
	$sql = "SELECT c.username,u.name,c.message,c.pub_date,c.id FROM chat c JOIN user u ON c.username = u.username  ORDER BY c.id";
	// echo $sql;
	$query = $conn->query($sql);
	$row = $query-> fetch_array();

	$arr = array();
	// if ($row) {
	// 	// header('http://localhost:88/chat_app/register.php');
	// 	// echo json_encode($row);
	// 	$data = array(
	// 			'id' => $row['id'],
	// 			'user_name' => $row['username'],
	// 			'message' => $row['message'],
	// 			'name' => $row['name'],
	// 			'pub_date' => $row['pub_date']
	// 	);
	// 	echo json_encode($data);
	// }
	// else{
	// 	header('HTTP/1.1 401 Login Fail');
	// }

	while ($row = $query -> fetch_array() ) {
		$arr[] = array(
				'id' => $row['id'],
				'user_name' => $row['username'],
				'message' => $row['message'],
				'name' => $row['name'],
				'pub_date' => $row['pub_date']
		);

	}
	
		echo json_encode($arr);


  ?>