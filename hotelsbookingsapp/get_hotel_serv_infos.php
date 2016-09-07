<?php
require_once('conn.php');
$idhotel=$_POST["idhotel"];
$sql="select tel,wifi,parking,restaurent,swimming_pool,gym,games_room,helicopter_landing from hotels where id = '$idhotel';";
if($res=mysqli_query($conn,$sql)){
	$row=mysqli_fetch_assoc($res);
	echo json_encode($row);
}else
	echo "Sorry there's no phone number found !";
mysqli_close($conn);
?>