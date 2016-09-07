<?php
require_once('conn.php');
 $code=$_POST["recov_code"];//"i3vT5okt";
 $newpass=$_POST["new_pass"];//"newpassword";
 $sql_query = "select mail from recoverycodes where recovcode like '$code';";  
 $result = mysqli_query($conn,$sql_query); 
  if(mysqli_num_rows($result) >0 )  
 {  
 $row = mysqli_fetch_assoc($result);  
 $email =$row["mail"];
 $sql_query1 = "update users set password='$newpass' where email like '$email' ;"; 
 $sql_query2 = "delete from recoverycodes where recovcode like '$code';"; 
 if(mysqli_query($conn,$sql_query1)&&mysqli_query($conn,$sql_query2))
	echo "Password changed successfully";
 else
	echo "Password changing is failed";
 }else{
	 echo "Code is not valid";
 }
 mysqli_close($conn);
?>