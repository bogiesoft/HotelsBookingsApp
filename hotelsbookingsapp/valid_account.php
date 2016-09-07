<?php
require "conn.php";
$mail="john@doe.com";
$sql_query="select firstname from users where '$mail' like email;";
$res=mysqli_query($conn,$sql_query);

if (mysqli_num_rows($res) > 0) {
	$row = mysqli_fetch_assoc($res);
	echo $row["firstname"];
}
mysqli_close($conn);
?>