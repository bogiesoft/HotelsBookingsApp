<?php
function test2room($idhotel,$sum1,$sum2,$arrday,$days){
$db_name="hotelsbookingsapp";
$mysql_user="root";
$mysql_pass="";
$server_name="localhost";
$conn=mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);
if($conn){
$tmpcp1=$sum1;
$tmpcp2=$sum2;
$result=array();
$found =false;
$sql1_query="SELECT HR.numroom FROM hotelsrooms HR
WHERE HR.idhotel='$idhotel' AND HR.capacity>='$tmpcp1'
AND ((HR.numroom IN(
SELECT ht.numroom FROM hotelsrooms ht WHERE isoccupied like 'no' and
ht.idhotel=HR.idhotel and ht.numroom NOT IN (
select numroom from reserv_room WHERE 
    idhotel=ht.idhotel
)))
OR HR.numroom IN(
SELECT RR1.numroom FROM reserv_room RR1 where RR1.idhotel=HR.idhotel and RR1.arrday > ('$arrday' + INTERVAL '$days' DAY)
) OR HR.numroom IN(
SELECT RR2.numroom FROM reserv_room RR2 where RR2.idhotel=HR.idhotel and ('$arrday') > ( RR2.arrday+ INTERVAL RR2.days DAY)
))
 ORDER BY HR.capacity ASC ;";

$r1 = mysqli_query($conn,$sql1_query);
if($r1){
	if($res=mysqli_fetch_assoc($r1)){
	$found=true;
	$result =array_fill(0,1,$res['numroom']);
}
}
if($found){
	
	$found=false;

$sql2_query="SELECT HR.numroom FROM hotelsrooms HR
WHERE HR.idhotel='$idhotel' AND HR.capacity>='$tmpcp2'
AND ((HR.numroom IN(
SELECT ht.numroom FROM hotelsrooms ht WHERE isoccupied like 'no' and
ht.idhotel=HR.idhotel and ht.numroom NOT IN (
select numroom from reserv_room WHERE 
    idhotel=ht.idhotel
)))
OR HR.numroom IN(
SELECT RR1.numroom FROM reserv_room RR1 where RR1.idhotel=HR.idhotel and RR1.arrday > ('$arrday' + INTERVAL '$days' DAY)
) OR HR.numroom IN(
SELECT RR2.numroom FROM reserv_room RR2 where RR2.idhotel=HR.idhotel and ('$arrday') > ( RR2.arrday+ INTERVAL RR2.days DAY)
))
 ORDER BY HR.capacity ASC ;";
 
 $r2 = mysqli_query($conn,$sql2_query);
 if($r2){
	 
	 if($res=mysqli_fetch_assoc($r2)){

		 while(in_array($res["numroom"],$result)){
			 $res=mysqli_fetch_assoc($r2);
		 }
		}
			
			 
			 if($res["numroom"]){
			 $found=true;
			 $result=array_merge($result,array_fill(0,1,$res["numroom"]));	 
			}
 }
 }else
	return false;

if(sizeof($result)==2)
	return true;
	else
		return false;
}else
	return false;
}
?>