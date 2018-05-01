<?php
//open connection to mysql db
    $conn = mysqli_connect("localhost","root","","zanimaux") or die("Error " . mysqli_error($conn));
mysqli_set_charset($conn, "utf8");

$contenant=$_GET['contenant'];
$id=$_GET['id'];

$sql = "update commentaires set contenant='$contenant',date =now() where id='$id'";

if (mysqli_query($conn, $sql)) {
    echo "successfully Modified";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);


?>