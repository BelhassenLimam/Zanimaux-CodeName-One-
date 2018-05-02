<?php
$conn = mysqli_connect("localhost","root","","nouvelle") or die("Error " . mysqli_error($conn));
mysqli_set_charset($conn, "utf8");
$data=array();
$idrdv = $_GET['idrdv'];
$sql = "delete from rendezvs where idrdv='$idrdv'";

if (mysqli_query($conn, $sql)) {
	echo "delete succeeded";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>