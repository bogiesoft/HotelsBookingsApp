 <?php  
 require "conn.php";  
 $user_mail =$_POST["login_mail"];  
 $user_pass =$_POST["login_pass"]; 
 $sql_query = "select id,firstname,lastname,isadmin from users where email like '$user_mail' and password like '$user_pass';";  
 $result = mysqli_query($conn,$sql_query);  
 if(mysqli_num_rows($result) >0 )  
 {  
 $row = mysqli_fetch_assoc($result);
 $id=$row["id"]; 
 $fn =$row["firstname"];
 $ln= $row["lastname"]; 
 $isadmin=$row["isadmin"];
 echo $id."  ".$isadmin."  "."Welcome ".$fn." ".$ln;  
 }  
 else  
 {   
 echo "User or password incorrect!";  
 }
mysqli_close($conn); 
 ?>  