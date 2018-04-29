<?php
   //open connection to mysql db
    $connection = mysqli_connect("localhost","root","root","Zanimaux") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");
    //fetch table rows from mysql db
$cin= $_GET['cin'];
    $sql = "select  idProduit, quantite, commande, dateCommande, idContenuPanier from ContenuPanier where (cin = '$cin' and commande=0) ";
    $result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));

    //create an array
 //create an array
    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = $row;
		
    }
  echo json_encode($emparray);

    //close the db connection
    mysqli_close($connection);
?>