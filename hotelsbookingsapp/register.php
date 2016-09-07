<?php
require "conn.php";
$email=$_POST["email"];
$first_name=$_POST["firstname"];
$last_name=$_POST["lastname"];
$password=$_POST["password"];
$isau=$_POST["isadmin"];
$sql_query = "select * from users where email like '$email';";
$result = mysqli_query($conn,$sql_query);  

if(mysqli_num_rows($result) >0 ){
echo "Already exists an account with this email address";
}else{
	$sql_query="insert into users values(null,'$email','$first_name','$last_name','$password','$isau');";
	if(!mysqli_query($conn,$sql_query)){echo "Error while insertion".mysqli_error($conn);}else{
		echo "Account created successfully";
	}
}
mysqli_close($conn);  
?>