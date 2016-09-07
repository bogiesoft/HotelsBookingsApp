<?php
require_once('conn.php');
$idhotel =$_POST['idhotel'];
$name=$_POST['imgname'];
$sql="update hotels_images set ismain=0 where idhotel='$idhotel' and name != '$name';";
if(mysqli_query($conn,$sql)){
$sql="update hotels_images set ismain=1 where idhotel='$idhotel' and name like '$name';";
if(mysqli_query($conn,$sql))
	echo "Hotel main image has been updated successfully";
else
	echo "Sorry there was a problem while updating hotel main image";
}else
	echo "Sorry there was a problem while updating hotel main image";
mysqli_close($conn);
?>