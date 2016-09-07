<?php
require_once('conn.php');
$id=$_POST["hotelid"];
$sql_query="select name,rating,tel,website,restaurent,parking,swimming_pool,
celebration_hall,wifi,gym, games_room,helicopter_landing,night_price,
restau_table_price,swimp_price,party_hall_price 
from hotels where id = '$id';";
if($res=mysqli_query($conn,$sql_query)){
	$row=mysqli_fetch_assoc($res);
	echo json_encode($row);
}
mysqli_close($conn);
?>