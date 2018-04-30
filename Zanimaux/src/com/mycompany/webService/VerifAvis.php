<?php
   //open connection to mysql db
    $connection = mysqli_connect("localhost","root","","zanimaux") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");
    //fetch table rows from mysql db
        $idParc=$_GET["idParc"];
	$cinUser=$_GET["cinUser"] ;
    
	
	$requete="select * from avis where (idParc = '$idParc') and (cinUser = '$cinUser')";
	$result=mysqli_query($connection, $requete) or die ("Error in Selecting " . mysqli_error($connection));
	
	$num=0;
	
 
    if($row =mysqli_fetch_assoc($result))
    {      
            
            $num=1;
            
                      
    }else{
        
            $num=0;

    }
	
	
	
    //close the db connection
    mysqli_close($connection);
?>