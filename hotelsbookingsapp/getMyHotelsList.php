<?php
require_once('conn.php');
$adminid=$_POST["adminid"];
$sql_query="select id,name from hotels where adminid = '$adminid';";
$r = mysqli_query($conn,$sql_query);
 $result=array();
 while($res = mysqli_fetch_array($r))
 array_push($result,array("id"=>$res['id'],
 "name"=>$res['name']));
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($conn);
?>