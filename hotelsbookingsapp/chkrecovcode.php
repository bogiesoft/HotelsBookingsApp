<?php
require_once('conn.php');
$code=$_POST['recov_code'];
$sql_query = "select * from recoverycodes where recovcode like '$code';";
$result = mysqli_query($conn,$sql_query);
if(mysqli_num_rows($result) >0 ) {
	echo "Code is valid";
}else{
	echo "Code is not valid";
}
mysqli_close($conn);
?>