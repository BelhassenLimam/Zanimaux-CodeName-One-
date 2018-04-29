<?php
//open connection to mysql db
    $conn = mysqli_connect("localhost","root","root","Zanimaux") or die("Error " . mysqli_error($conn));
mysqli_set_charset($conn, "utf8");

$quantite=$_GET['quantite'];
$idProduit=$_GET['idP'];
$idContenuPanier=$_GET['idCP'];
$prix=$_GET['prix']*$quantite;
$cin=$_GET['cin'];

$sql = "update ContenuPanier set quantite=quantite+$quantite where idContenuPanier='$idContenuPanier'";
$sql1 = "update Produit set quantite=quantite-$quantite where idProduit='$idProduit'";
$sql2 = "update Panier set somme=somme+$prix where cin='$cin'";
if ((mysqli_query($conn, $sql))&&(mysqli_query($conn, $sql1))&&(mysqli_query($conn, $sql2))) {
    echo "successfully Modified";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);


?>