<?php
   //open connection to mysql db
    $connection = mysqli_connect("localhost","root","","zanimaux") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");
    //fetch table rows from mysql db
	$cin=$_GET["cin"] ;
    $idA=$_GET["idA"];
	
	$requete="INSERT INTO annonce_favoris (cin,idA) VALUES ('$cin',$idA)";
	mysqli_query($connection, $requete) or die ("Error in Selecting " . mysqli_error($connection));
	
	echo"succes";
    //close the db connection
    mysqli_close($connection);
?>