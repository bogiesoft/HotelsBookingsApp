<?php
//require_once('conn.php');
function Book2room($idhotel,$iduser,$sum1,$sum2,
$nba1,$nba2,$arrday,$days,$cost1,$cost2){
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
	echo " Sorry there's no enough rooms";

if(sizeof($result)==2){
	$nbk1=$sum1-$nba1;
	$nbk2=$sum2-$nba2;
	$sql1="insert INTO reserv_room values('$idhotel','$iduser','$result[0]','$nba1','$nbk1','$arrday','$days','$days','$cost1');";
	$sql2="insert INTO reserv_room values('$idhotel','$iduser','$result[1]','$nba2','$nbk2','$arrday','$days','$days','$cost2');";
	if(mysqli_query($conn,$sql1)&&mysqli_query($conn,$sql2))
	echo"Operation completed successfully ! \n Your rooms numbers provided are: \n"."Room 1 : ".$result[0]."\n".
	"Room 2 : ".$result[1]."\n";
		else
		echo "Operation failed !";
	}else
		echo "Sorry there's no enough rooms";

mysqli_close($conn);
}
}
?>