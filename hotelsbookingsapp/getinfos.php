<?php
require_once('conn.php');
$id=$_POST["user_id"];
$db_query="select email,firstname,lastname from users where id='$id';";
$res= mysqli_query($conn,$db_query);

if(mysqli_num_rows($res)>0){
	$row=mysqli_fetch_assoc($res);
	$mail=$row["email"];
	$fn= $row["firstname"];
	$ln= $row["lastname"];
	echo $mail."  ".$fn."  ".$ln;
}
	else
		echo "Invalid  Account";
mysqli_close($conn);
?>