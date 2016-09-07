<?php
require_once('conn.php');
$numroom=$_POST["numroom"];
$numhotel=$_POST["idhotel"];
$capacity=$_POST["capacity"];
//$roomprice=$_POST["price"];

$sql_query="select count(*) from hotelsrooms where numroom = '$numroom' and idhotel ='$numhotel';";

$res=mysqli_query($conn,$sql_query);
$data=mysqli_fetch_assoc($res);

if($data['count(*)']==0){
	$sql_query="insert into hotelsrooms values ('$numroom','$numhotel','$capacity','No');";
	
if(!mysqli_query($conn,$sql_query))
	echo "Error while inserting the room number '$numroom'";
}

mysqli_close($conn);
?>