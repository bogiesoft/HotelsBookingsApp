<?php
require_once('conn.php');
$id=$_POST['user_id'];
$oldpass=$_POST['old_pass'];
$newpass=$_POST['new_pass'];
$sql_query="select count(*) from users where id='$id' and password like '$oldpass';";
$res=mysqli_query($conn,$sql_query);
$data=mysqli_fetch_assoc($res);
if($data['count(*)']==0)
	echo "The Old Password is wrong";
else{
$sql_query="update users set password='$newpass' where id='$id' and password like '$oldpass';";
if(mysqli_query($conn,$sql_query))
	echo "Password changed successfully";
}
mysqli_close($conn);
?>