<?php
   //open connection to mysql db
    $connection = mysqli_connect("localhost","root","root","zanimauxfinal") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");
    //fetch table rows from mysql db
	$idParc=$_GET["idParc"] ;
        $avis=$_GET["avis"] ;
         $cinUser=$_GET["cinUser"];
	
	$requete="INSERT INTO avis (idParc,avis,cinUser) VALUES ('$idParc','$avis','$cinUser')";
	mysqli_query($connection, $requete) or die ("Error in Selecting " . mysqli_error($connection));
	
    //close the db connection
    mysqli_close($connection);
?>