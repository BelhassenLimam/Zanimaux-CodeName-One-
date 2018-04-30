<?php
   //open connection to mysql db
    $connection = mysqli_connect("localhost","root","","zanimaux") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");
    //fetch table rows from mysql db
	$cin=$_GET["cin"] ;
    $idEvt=$_GET["idEvt"];
	
	$requete="select * from participation where (idEvt = '$idEvt') and (cin = '$cin')";
	$result=mysqli_query($connection, $requete) or die ("Error in Selecting " . mysqli_error($connection));
	
	$num=0;
	
 $e="";
    if($row =mysqli_fetch_assoc($result))
    {      
            $e="existe";
            $num=1;
            
                      
    }else{
        $e="n existe pas";
        $num=0;

    }
	echo $e;
	
	
    //close the db connection
    mysqli_close($connection);
?>