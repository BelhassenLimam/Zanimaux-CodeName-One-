<?php
$conn = mysqli_connect("localhost","root","","nouvelle") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");



$vu=$_GET['vu'];
$cin=$_GET['cin'];


$sql = "update notif_user_rdv set vu='$vu' where cin='$cin'";

if (mysqli_query($conn, $sql)) {
    echo "successfully Modified";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);


?>

