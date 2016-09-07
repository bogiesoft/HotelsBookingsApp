<?php
require_once('conn.php');
$idhotel=$_POST['idhotel'];
$sql_query="SELECT HR.numroom FROM hotelsrooms HR
WHERE HR.idhotel='$idhotel'
AND HR.numroom NOT IN(
    SELECT numroom FROM reserv_room RR where RR.idhotel=HR.idhotel
)
AND HR.isoccupied LIKE 'no' ORDER BY HR.numroom ASC ;";

$r = mysqli_query($conn,$sql_query);
if(!$r)
	echo mysqli_error($conn);
$result=array();
 
 while($res = mysqli_fetch_array($r))
 array_push($result,array("numroom"=>$res['numroom']));
 
 echo json_encode(array("result"=>$result));
	mysqli_close($conn);

?>