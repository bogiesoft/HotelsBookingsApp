<?php
function test5room($idhotel,$sum1,$sum2,$sum3,$sum4,$sum5,$arrday,$days){
$db_name="hotelsbookingsapp";
$mysql_user="root";
$mysql_pass="";
$server_name="localhost";
$conn=mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);
if($conn){

$tmpcp1=$sum1;

$tmpcp2=$sum2;

$tmpcp3=$sum3;

$tmpcp4=$sum4;

$tmpcp5=$sum5;

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


 if($found){

	 $found=false;

$sql3_query="SELECT HR.numroom FROM hotelsrooms HR
WHERE HR.idhotel='$idhotel' AND HR.capacity>='$tmpcp3'
AND ((HR.numroom IN(
SELECT ht.numroom FROM hotelsrooms ht WHERE isoccupied like 'no' and
ht.idhotel=HR.idhotel and ht.numroom NOT IN (
select numroom from reserv_room WHERE 
    idhotel=ht.idhotel
)))
OR HR.numroom IN(
SELECT RR1.numroom FROM reserv_room RR1 where HR.idhotel and RR1.arrday > ('$arrday' + INTERVAL '$days' DAY)
) OR HR.numroom IN(
SELECT RR2.numroom FROM reserv_room RR2 where  RR2.idhotel=HR.idhotel and ('$arrday') > ( RR2.arrday+ INTERVAL RR2.days DAY)
))
 ORDER BY HR.capacity ASC ;";
 $r3 = mysqli_query($conn,$sql3_query);
 if($r3){
	 
	 if($res=mysqli_fetch_assoc($r3)){

		 while(in_array($res["numroom"],$result)){
$res=mysqli_fetch_assoc($r3);			 
		 }
		}
		
			 
			 if($res["numroom"]){
			 $found=true;
			 $result=array_merge($result,array_fill(0,1,$res["numroom"]));	 
			}
}			

 
if($found){

$found=true;	

$sql4_query="SELECT HR.numroom FROM hotelsrooms HR
WHERE HR.idhotel='$idhotel' AND HR.capacity>='$tmpcp4'
AND ((HR.numroom IN(
SELECT ht.numroom FROM hotelsrooms ht WHERE isoccupied like 'no' and
ht.idhotel=HR.idhotel and ht.numroom NOT IN (
select numroom from reserv_room WHERE 
    idhotel=ht.idhotel
)))
OR HR.numroom IN(
SELECT RR1.numroom FROM reserv_room RR1 where RR1.idhotel=HR.idhotel and RR1.arrday > ('$arrday' + INTERVAL '$days' DAY)
) OR HR.numroom IN(
SELECT RR2.numroom FROM reserv_room RR2 where  RR2.idhotel=HR.idhotel and ('$arrday') > ( RR2.arrday+ INTERVAL RR2.days DAY)
))
 ORDER BY HR.capacity ASC ;";
 $r4 = mysqli_query($conn,$sql4_query);
 if($r4){
	 
	 if($res=mysqli_fetch_assoc($r4)){

		 while(in_array($res["numroom"],$result)){
			 $res=mysqli_fetch_assoc($r4);
		 }
		}
			 
			 if($res["numroom"]){
			 $found=true;
			 $result=array_merge($result,array_fill(0,1,$res["numroom"]));	 
			}
}
if($found){

$found=true;	

$sql5_query="SELECT HR.numroom FROM hotelsrooms HR
WHERE HR.idhotel='$idhotel' AND HR.capacity>='$tmpcp5'
AND ((HR.numroom IN(
SELECT ht.numroom FROM hotelsrooms ht WHERE isoccupied like 'no' and
ht.idhotel=HR.idhotel and ht.numroom NOT IN (
select numroom from reserv_room WHERE 
    idhotel=ht.idhotel
)))
OR HR.numroom IN(
SELECT RR1.numroom FROM reserv_room RR1 where RR1.idhotel=HR.idhotel and RR1.arrday > ('$arrday' + INTERVAL '$days' DAY)
) OR HR.numroom IN(
SELECT RR2.numroom FROM reserv_room RR2 where  RR2.idhotel=HR.idhotel and ('$arrday') > ( RR2.arrday+ INTERVAL RR2.days DAY)
))
 ORDER BY HR.capacity ASC ;";
 $r5 = mysqli_query($conn,$sql5_query);
 if($r5){
	 
	 if($res=mysqli_fetch_assoc($r5)){

		 while(in_array($res["numroom"],$result)){
			 $res=mysqli_fetch_assoc($r5);
		 }
		}
			 
			 if($res["numroom"]){
			 $found=true;
			 $result=array_merge($result,array_fill(0,1,$res["numroom"]));	 
			}
}
}else
	return false;
}else
	return false;
 }else
	return false;
 }else
	return false;
if(sizeof($result)==5){
	return true;
	}else
		return false;
}else
	return false;
mysqli_close($conn);
}
?>
