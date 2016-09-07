<?php
require_once('conn.php');
$roomnum=$_POST["roomnum"];//"11";
$hotelid=$_POST["hotelid"];//"1";
$capacity=$_POST["capacity"];//"3";
$isoccup=$_POST["isoccup"];//"Yes";
//$night_price=$_POST["night_price"];//"1";

$sql_query="update hotelsrooms set capacity ='$capacity', isoccupied= '$isoccup' where numroom='$roomnum' and idhotel='$hotelid';";
 if(mysqli_query($conn,$sql_query))
	 echo "Room updated successfully";
 else
	 echo "Room is not updated please retry";
 mysqli_close($conn);
?>