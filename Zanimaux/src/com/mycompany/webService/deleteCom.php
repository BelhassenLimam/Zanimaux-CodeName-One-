<?php
$conn = mysqli_connect("localhost","root","","zanimaux") or die("Error " . mysqli_error($conn));
mysqli_set_charset($conn, "utf8");
$data=array();
$id = $_GET['id'];
$sql = "delete from commentaires where id='$id'";

if (mysqli_query($conn, $sql)) {
	echo "delete succeeded";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>