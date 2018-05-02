<?php
   //open connection to mysql db
    $connection = mysqli_connect("localhost","root","","nouvelle") or die("Error " . mysqli_error($connection));
mysqli_set_charset($connection, "utf8");
    //fetch table rows from mysql db
    $user=$_GET["username"] ;
    $pwd=$_GET["pwd"];
    $sql = "select cin,username, username_canonical, email, email_canonical, enabled, password, last_login, roles from fos_user  where (username ='$user')";
    $result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));

    //create an array
 //create an array
    $emparray = array();
    if($row =mysqli_fetch_assoc($result))
    {       $r = $row['password'];
            if (password_verify($pwd, $r))
            {
            $emparray[] = $row;
            echo json_encode($emparray);
            }
            else
            {
                echo "MOT DE PASSE INVALIDE";
            }
           
    }else{
        
            echo"NOM D'UTILISATEUR INVALIDE";
    }
  

    //close the db connection
    mysqli_close($connection);
?>