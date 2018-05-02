
<?php
 $conn = mysqli_connect("localhost","root","","nouvelle") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");


$cin=$_GET['cin'];

$id_notif=$_GET['id_notif'];
$vu=$_GET['vu'];



$sql = "INSERT INTO notif_user_rdv (cin,id_notif,vu) VALUES ('$cin','$id_notif','$vu')";


if (mysqli_query($conn, $sql)) {
    echo "successfully added";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>