
<?php
 $conn = mysqli_connect("localhost","root","","nouvelle") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");



$idrdv=$_GET['idrdv'];
$vu=$_GET['vu'];



$sql = "INSERT INTO notification_rdv (idrdv,vu) VALUES ('$idrdv','$vu')";


if (mysqli_query($conn, $sql)) {
    echo "successfully added";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>