<?php
require_once('conn.php');
$idhotel=$_POST["idhotel"];
$sql_query="select numroom,capacity,isoccupied from hotelsrooms where idhotel = '$idhotel' ORDER BY numroom ASC ;";
$r = mysqli_query($conn,$sql_query);
if(!$r)
	echo mysqli_error($conn);
else{
$result=array();
 
 while($res = mysqli_fetch_array($r))
 array_push($result,array(
"numroom"=>$res['numroom'],"capacity"=>$res['capacity'],
"isoccupied"=>$res['isoccupied'])
);
 echo json_encode(array("result"=>$result));
}
mysqli_close($conn);
?>