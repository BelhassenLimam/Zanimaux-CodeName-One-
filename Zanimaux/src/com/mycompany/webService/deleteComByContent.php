<?php
$conn = mysqli_connect("localhost","root","","zanimaux") or die("Error " . mysqli_error($conn));
mysqli_set_charset($conn, "utf8");
$data=array();
$contenant = $_GET['contenant'];
$sql = "delete from commentaires where contenant='$contenant'";

if (mysqli_query($conn, $sql)) {
	echo "delete succeeded";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>