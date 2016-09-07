<?php
require_once('conn.php');
$idhotel=$_POST["idhotel"];
$idroom=$_POST["idroom"];

$sql_query="delete from hotelsrooms where numroom='$idroom' and idhotel='$idhotel';";
if(!mysqli_query($conn,$sql_query))
	echo "Deletation failure at the room number ".$idroom;
mysqli_close($conn);
?>