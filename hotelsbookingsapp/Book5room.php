<?php
//require_once('conn.php');
function Book5room($idhotel,$iduser,$sum1,$sum2,$sum3,$sum4,$sum5,$nba1,
$nba2,$nba3,$nba4,$nba5,$arrday,$days,$cost1,$cost2,$cost3,$cost4,$cost5){
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

$found=false;	

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

$found=false;	

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
	echo " Sorry there's no enough rooms";
}else
	echo " Sorry there's no enough rooms";
 }else
	 echo " Sorry there's no enough rooms";
 }else
	echo " Sorry there's no enough rooms";
if(sizeof($result)==5){
	$nbk1=$sum1-$nba1;
	$nbk2=$sum2-$nba2;
	$nbk3=$sum3-$nba3;
	$nbk4=$sum4-$nba4;
	$nbk5=$sum5-$nba5;
	$sql1="insert INTO reserv_room values('$idhotel','$iduser','$result[0]','$nba1','$nbk1','$arrday','$days','$days','$cost1');";
	$sql2="insert INTO reserv_room values('$idhotel','$iduser','$result[1]','$nba2','$nbk2','$arrday','$days','$days','$cost2');";
	$sql3="insert INTO reserv_room values('$idhotel','$iduser','$result[2]','$nba3','$nbk3','$arrday','$days','$days','$cost3');";
	$sql4="insert INTO reserv_room values('$idhotel','$iduser','$result[3]','$nba4','$nbk4','$arrday','$days','$days','$cost4');";
	$sql5="insert INTO reserv_room values('$idhotel','$iduser','$result[4]','$nba5','$nbk5','$arrday','$days','$days','$cost5');";
	if(mysqli_query($conn,$sql1)&&mysqli_query($conn,$sql2)&&mysqli_query($conn,$sql3)&&mysqli_query($conn,$sql4)&&mysqli_query($conn,$sql5))
	echo"Operation completed successfully ! \n Your rooms numbers provided are: \n"."Room 1 : ".$result[0]."\n".
	"Room 2 : ".$result[1]."\n"."Room 3 : ".$result[2]."\n"."Room 4 : ".$result[3]."\n"."Room 5 : ". $result[4]."\n";
	else
		echo "Operation failed !";

	}else
		echo "Sorry there's no enough rooms";

mysqli_close($conn);
}
}
?>
