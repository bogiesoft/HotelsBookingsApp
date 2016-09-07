<?php
require_once('conn.php');
$idhotel =$_POST['idhotel'];
$name=$_POST['imgname'];
$sql="delete from hotels_images where idhotel='$idhotel' and name like '$name';";
$file="IMG_Hotels/$name";
if(mysqli_query($conn,$sql))
		if(unlink($file))
			echo "Image deleted successfully";
		else echo "There's a problem while image deletation";
else
	echo "Image not deleted properly";
mysqli_close($conn);
?>