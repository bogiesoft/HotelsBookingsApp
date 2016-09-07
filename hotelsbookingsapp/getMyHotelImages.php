<?php
require_once('conn.php');
$idhotel=$_POST["idhotel"];
$sql="select name from hotels_images where idhotel='$idhotel' ORDER BY name ASC;";
if(!$r=mysqli_query($conn,$sql))
	echo "Error while loading the list of Images";
else{
$result=array(); 
 while($res = mysqli_fetch_array($r))
 array_push($result,array("imgname"=>$res['name']));
 
 echo json_encode(array("result"=>$result));
}
mysqli_close($conn);
?>