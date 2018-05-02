<?php
   //open connection to mysql db
    $connection = mysqli_connect("localhost","root","root","zanimauxfinal") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");
    //fetch table rows from mysql db
    $cinUser=$_GET["cinUser"] ;
    $idParc=$_GET["idParc"];
    
    $requete="select * from avis where (idParc = '$idParc') and (cinUser = '$cinUser')";
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