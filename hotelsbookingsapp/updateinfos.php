<?php
require_once('conn.php');
$id=$_POST["user_id"];
$mail=$_POST["user_mail"];
$fn=$_POST["user_fn"];
$ln=$_POST["user_ln"];

$sql_query="select count(*) from users where email like '$mail' and id != '$id';";
$res=mysqli_query($conn,$sql_query);
$data=mysqli_fetch_assoc($res);

if($data['count(*)']==0){
$sql_query="update users set email='$mail', firstname='$fn', lastname='$ln' where id='$id';";
if(mysqli_query($conn,$sql_query))
echo "Account informations successfully updated";
else
	echo "Account Update failed, please retry";
}else
	echo "This email is not authorised";
mysqli_close($conn);
?>