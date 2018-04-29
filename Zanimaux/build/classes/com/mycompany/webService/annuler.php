<?php
   //open connection to mysql db
    $connection = mysqli_connect("localhost","root","","zanimaux") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");
    //fetch table rows from mysql db
	$cin=$_GET["cin"] ;
    $idEvt=$_GET["idEvt"];
	
	$requete="delete from participation where cin= '$cin' and idEvt=$idEvt";
	mysqli_query($connection, $requete) or die ("Error in Selecting " . mysqli_error($connection));
	
	$up="UPDATE evenement SET  nbParticipants=nbParticipants-1 where idEvt=$idEvt";
		mysqli_query($connection, $up) or die ("Error in Selecting " . mysqli_error($connection));
	echo"succes";
    //close the db connection
    mysqli_close($connection);
?>