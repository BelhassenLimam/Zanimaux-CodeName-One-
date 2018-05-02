
<?php
 $conn = mysqli_connect("localhost","root","","nouvelle") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");


$immatriculecabinet=$_GET['immatriculecabinet'];

$cin=$_GET['cin'];
$heurerdv=$_GET['heurerdv'];


$h = date('Y-m-d H:i:s',strtotime($heurerdv));

$sql = "INSERT INTO rendezvs(cin,immatriculecabinet,heurerdv)
VALUES('$cin','$immatriculecabinet','$h')";


if (mysqli_query($conn, $sql)) {
    echo "successfully added";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>