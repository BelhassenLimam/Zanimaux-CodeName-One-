
<?php
    $conn = mysqli_connect("localhost","root","","zanimaux") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");

$cin=$_GET['cin'];
$contenant=$_GET['contenant'];
$refuge=$_GET['refuge'];

//$d=$_GET['date'];
//$e=$_GET['email'];
$sql = "INSERT INTO commentaires ( refuge,cin , contenant ,date) VALUES('$refuge','$cin','$contenant',now())";

//$sql = "INSERT INTO materiel (prix)VALUES('$prix')";
if (mysqli_query($conn, $sql)) {
    echo "successfully added";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>