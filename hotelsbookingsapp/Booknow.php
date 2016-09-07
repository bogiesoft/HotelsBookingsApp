<?php
require_once('conn.php');

require_once('Book1room.php');
require_once('Book2room.php');
require_once('Book3room.php');
require_once('Book4room.php');
require_once('Book5room.php');

$idhotel=$_POST["idhotel"];
$serv=$_POST["serv"];

$iduser=$_POST["iduser"];
$arrday=$_POST["arrday"];
$nbdays=$_POST["nbdays"];
$arrtime=$_POST["arrtime"];
$nbpers=$_POST["nbpers"];

$nba1=$_POST["nba1"];
$nba2=$_POST["nba2"];
$nba3=$_POST["nba3"];
$nba4=$_POST["nba4"];
$nba5=$_POST["nba5"];

$nbk1=$_POST["nbk1"];
$nbk2=$_POST["nbk2"];
$nbk3=$_POST["nbk3"];
$nbk4=$_POST["nbk4"];
$nbk5=$_POST["nbk5"];

//echo "nba1= ".$nba1."\n"."nba2= ".$nba2."\n"."nba3= ".$nba3."\n"."nba4= ".$nba4."\n"."nba5= ".$nba5."\n".
//"nbk1= ".$nbk1."\n"."nbk2= ".$nbk2."\n"."nbk3= ".$nbk3."\n"."nka4= ".$nbk4."\n"."nbk5= ".$nbk5."\n";


function calc_cost_reserv_room($uprice,$ndays,$na1,$nk1){//,$na2,$nk2,$na3,$nk3,$na4,$nk4,$na5,$nk5){
	$cost=0;
		if($na1!="none")
		$cost+=$na1*$uprice;
		if($nk1!="none")
		$cost+=$nk1*($uprice/2);

	return $cost*$ndays;
}

function calc_cost_reserv_swimp($uprice,$na1,$nk1){
		$cost=0;
		if($na1!="none")
		$cost+=$na1*$uprice;
		
		if($nk1!="none")
		$cost+=$nk1*($uprice/2);
	return $cost;
}


function calc_cost_reserv_restau($uprice,$nbpers){
	if(($nbpers%4)!=0){
		$nbtabs=($nbpers-($nbpers%4))/4;
		$nbtabs++;
	}else
		$nbtabs=($nbpers/4);
return $nbtabs*$uprice;
}

if($serv=="rooms"){
	
	$sum1=0;
if($nba1!="none")
	$sum1+=$nba1;
if($nbk1!="none")
	$sum1+=$nbk1;

$sum2=0;
if($nba2!="none")
	$sum2+=$nba2;
if($nbk2!="none")
	$sum2+=$nbk2;

$sum3=0;
if($nba3!="none")
	$sum3+=$nba3;
if($nbk3!="none")
	$sum3+=$nbk3;

$sum4=0;
if($nba4!="none")
	$sum4+=$nba4;
if($nbk4!="none")
	$sum4+=$nbk4;

$sum5=0;
if($nba5!="none")
	$sum5+=$nba5;
if($nbk5!="none")
	$sum5+=$nbk5;
	
		$sql="select night_price as uprice from hotels where id = '$idhotel';";
		if($res=mysqli_query($conn,$sql)){
		$uprice=mysqli_fetch_assoc($res);
		$upri=$uprice['uprice'];
	
	if($sum1!=0&&$sum2!=0&&$sum2!=0&&$sum4!=0&&$sum5!=0){
		$cost1=calc_cost_reserv_room($upri,$nbdays,$nba1,$nbk1);
		$cost2=calc_cost_reserv_room($upri,$nbdays,$nba2,$nbk2);
		$cost3=calc_cost_reserv_room($upri,$nbdays,$nba3,$nbk3);
		$cost4=calc_cost_reserv_room($upri,$nbdays,$nba4,$nbk4);
		$cost5=calc_cost_reserv_room($upri,$nbdays,$nba5,$nbk5);
		
		Book5room($idhotel,$iduser,$sum1,$sum2,$sum3,$sum4,$sum5,$nba1,
$nba2,$nba3,$nba4,$nba5,$arrday,$nbdays,$cost1,$cost2,$cost3,$cost4,$cost5);

	}else if($sum1!=0&&$sum2!=0&&$sum2!=0&&$sum4!=0){
		$cost1=calc_cost_reserv_room($upri,$nbdays,$nba1,$nbk1);
		$cost2=calc_cost_reserv_room($upri,$nbdays,$nba2,$nbk2);
		$cost3=calc_cost_reserv_room($upri,$nbdays,$nba3,$nbk3);
		$cost4=calc_cost_reserv_room($upri,$nbdays,$nba4,$nbk4);
		Book4room($idhotel,$iduser,$sum1,$sum2,$sum3,$sum4,$nba1,
$nba2,$nba3,$nba4,$arrday,$nbdays,$cost1,$cost2,$cost3,$cost4);

	}else if($sum1!=0&&$sum2!=0&&$sum3!=0){
		$cost1=calc_cost_reserv_room($upri,$nbdays,$nba1,$nbk1);
		$cost2=calc_cost_reserv_room($upri,$nbdays,$nba2,$nbk2);
		$cost3=calc_cost_reserv_room($upri,$nbdays,$nba3,$nbk3);
		Book3room($idhotel,$iduser,$sum1,$sum2,$sum3,
$nba1,$nba2,$nba3,$arrday,$nbdays,$cost1,$cost2,$cost3);

	}else if($sum1!=0&&$sum2!=0){
		$cost1=calc_cost_reserv_room($upri,$nbdays,$nba1,$nbk1);
		$cost2=calc_cost_reserv_room($upri,$nbdays,$nba2,$nbk2);
		Book2room($idhotel,$iduser,$sum1,$sum2,$nba1,$nba2,
		$arrday,$nbdays,$cost1,$cost2);

	}else{
		$cost1=calc_cost_reserv_room($upri,$nbdays,$nba1,$nbk1);
		Book1room($idhotel,$iduser,$sum1,$nba1,$arrday,$nbdays,$cost1);

	}
		}else
			echo"Operation failed";
}else if($serv=="restau"){
		$sql="select restau_table_price as uprice from hotels WHERE id= '$idhotel';";
		if($res=mysqli_query($conn,$sql)){
		$uprice=mysqli_fetch_assoc($res);
		$upri=$uprice['uprice'];
	$cost1=calc_cost_reserv_restau($upri,$nbpers);
	$sql1="Insert into reserv_restau values('$idhotel','$iduser','$arrtime','$nbpers','$cost1');";
	if(mysqli_query($conn,$sql1))
		echo "Operation completed successfully";
	else echo "Operation failed please retry !";
		}else 
			echo "Opreation failed";
}else if($serv=="swimp"){
		$sql="select swimp_price as uprice from hotels WHERE id= '$idhotel';";
		if($res=mysqli_query($conn,$sql)){
		$uprice=mysqli_fetch_assoc($res);
		$upri=$uprice['uprice'];
		$cost1=calc_cost_reserv_swimp($upri,$nba1,$nbk1);
	$sql1="Insert into reserv_swimp values(null,'$idhotel','$iduser','$arrday','$nba1','$nbk1','$cost1');";
	if(mysqli_query($conn,$sql1))
		echo "Operation completed successfully";
	else echo "Operation failed please retry !";
		}else 
			echo "Opreation failed";
}else{
	$sql="select count(*) from reserv_partyhall WHERE arrday=('$arrday');";
	if($res=mysqli_query($conn,$sql))
		$exists=mysqli_fetch_assoc($res);
	if($exists['count(*)']==0){
		$sql="select party_hall_price as uprice from hotels WHERE id= '$idhotel';";
		if($res=mysqli_query($conn,$sql)){
		$uprice=mysqli_fetch_assoc($res);
		$cost1=$uprice['uprice'];
		$sql1="Insert into reserv_partyhall values('$idhotel','$iduser','$arrday','$nbpers','$cost1');";
		if(mysqli_query($conn,$sql1))
		echo "Operation completed successfully";
	else echo "Operation failed please retry !";
		}else 
			echo "Opreation failed";
	}else
		echo "Sorry the celebration hall is aleardy reserved in this days, please seek in another day";
		
}
mysqli_close($conn);	
?>