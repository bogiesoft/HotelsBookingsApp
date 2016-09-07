<?php
require_once('conn.php');
$serv=$_POST["serv"];
$idhotel=$_POST["idhotel"];
$period=$_POST["period"];

//echo "serv= ".$serv."\n"."idhotel= ".$idhotel."\n"."period= ".$period."\n";

if($serv=="rooms"){
if($period=="all"){
	$sql="select RR.numroom,RR.nbadults,RR.nbkids,RR.arrday,RR.days,RR.daysleft,RR.cost from reserv_room  RR WHERE 
 RR.idhotel='$idhotel' ORDER BY RR.arrday DESC;"; 
}else if($period=="new"){
	$sql="select RR.numroom,RR.nbadults,RR.nbkids,RR.arrday,RR.days,RR.daysleft,RR.cost from reserv_room  RR WHERE 
 RR.idhotel='$idhotel' and RR.arrday>=(CURRENT_DATE) ORDER BY RR.arrday DESC;"; 
}else if($period=="today"){
	$sql="select RR.numroom,RR.nbadults,RR.nbkids,RR.arrday,RR.days,RR.daysleft,RR.cost from reserv_room  RR WHERE 
 RR.idhotel='$idhotel' and RR.arrday=(CURRENT_DATE);";
}else if($period==7){
	$sql="select RR.numroom,RR.nbadults,RR.nbkids,RR.arrday,RR.days,RR.daysleft,RR.cost from reserv_room  RR WHERE 
 RR.idhotel='$idhotel' and ((CURRENT_DATE-INTERVAL 7 DAY)<=RR.arrday)and(RR.arrday<(CURRENT_DATE)) ORDER BY RR.arrday ASC;";
}else if($period==14){
	$sql="select RR.numroom,RR.nbadults,RR.nbkids,RR.arrday,RR.days,RR.daysleft,RR.cost from reserv_room  RR WHERE 
 RR.idhotel='$idhotel' and ((CURRENT_DATE-INTERVAL 14 DAY)<=RR.arrday)and(RR.arrday<(CURRENT_DATE)) ORDER BY RR.arrday ASC;";
}else if($period==21){
	$sql="select RR.numroom,RR.nbadults,RR.nbkids,RR.arrday,RR.days,RR.daysleft,RR.cost from reserv_room  RR WHERE 
 RR.idhotel='$idhotel' and ((CURRENT_DATE-INTERVAL 21 DAY)<=RR.arrday)and(RR.arrday<(CURRENT_DATE)) ORDER BY RR.arrday ASC;";
}else{
	$sql="select RR.numroom,RR.nbadults,RR.nbkids,RR.arrday,RR.days,RR.daysleft,RR.cost from reserv_room  RR WHERE 
 RR.idhotel='$idhotel' and ((CURRENT_DATE-INTERVAL 31 DAY)<=RR.arrday)and(RR.arrday<(CURRENT_DATE)) ORDER BY RR.arrday ASC;";
}
	
$r = mysqli_query($conn,$sql);
if(!$r)
	echo mysqli_error($conn);
else{
$result=array();
 
 while($res = mysqli_fetch_array($r))
 array_push($result,array(
"col1"=>$res['numroom'],"col2"=>$res['nbadults'],"col3"=>$res['nbkids'],
"col4"=>$res['arrday'],"col5"=>$res['days'],"col6"=>$res['daysleft'],"col7"=>$res['cost'])
);
 echo json_encode(array("result"=>$result));
}
}else if ($serv=="restau"){
	

if($period=="all"){
	$sql="SELECT RR.nbpers, RR.arrtime, RR.cost from reserv_restau RR 
where RR.idhotel='$idhotel' ORDER BY RR.arrtime DESC;";
}else if($period=="new"){
	$sql="SELECT RR.nbpers, RR.arrtime, RR.cost from reserv_restau RR 
where RR.idhotel='$idhotel' and (RR.arrtime>=(CURRENT_TIMESTAMP)) ORDER BY RR.arrtime DESC;";
}else if($period=="today"){
	$sql="SELECT RR.nbpers, RR.arrtime, RR.cost from reserv_restau RR 
where RR.idhotel='$idhotel' and ((CURRENT_TIMESTAMP-INTERVAL 1 DAY)<=RR.arrtime)and(RR.arrtime<=(CURRENT_TIMESTAMP)) 
 ORDER BY RR.arrtime DESC ;";
}else if($period==7){
	$sql="SELECT RR.nbpers, RR.arrtime, RR.cost from reserv_restau RR 
where RR.idhotel='$idhotel' and ((CURRENT_TIMESTAMP-INTERVAL 7 DAY)<=RR.arrtime)and
(RR.arrtime<=((CURRENT_TIMESTAMP)-INTERVAL 1 DAY)) ORDER BY RR.arrtime ASC;";
}else if($period==14){
	$sql="SELECT RR.nbpers, RR.arrtime, RR.cost from reserv_restau RR 
where RR.idhotel='$idhotel' and ((CURRENT_TIMESTAMP-INTERVAL 14 DAY)<=RR.arrtime)and
(RR.arrtime<=((CURRENT_TIMESTAMP)-INTERVAL 1 DAY)) ORDER BY RR.arrtime ASC;";
}else if($period==21){
	$sql="SELECT RR.nbpers, RR.arrtime, RR.cost from reserv_restau RR 
where RR.idhotel='$idhotel' and ((CURRENT_TIMESTAMP-INTERVAL 21 DAY)<=RR.arrtime)and
(RR.arrtime<=((CURRENT_TIMESTAMP)-INTERVAL 1 DAY)) ORDER BY RR.arrtime ASC;";
}else{
	$sql="SELECT RR.nbpers, RR.arrtime, RR.cost from reserv_restau RR 
where RR.idhotel='$idhotel' and ((CURRENT_TIMESTAMP-INTERVAL 31 DAY)<=RR.arrtime)and
(RR.arrtime<=((CURRENT_TIMESTAMP)-INTERVAL 1 DAY)) ORDER BY RR.arrtime ASC;";
}

$r = mysqli_query($conn,$sql);
if(!$r)
	echo mysqli_error($conn);
else{
$result=array();
 
 while($res = mysqli_fetch_array($r))
 array_push($result,array(
"col1"=>$res['nbpers'],"col2"=>$res['arrtime'],"col3"=>$res['cost']));
 echo json_encode(array("result"=>$result));
}
	
}else if($serv=="swimp"){
	

if($period=="all"){
	$sql="select SR.nbadults, SR.nbkids, SR.arrday,SR.cost from reserv_swimp SR 
where SR.idhotel='$idhotel' ORDER BY SR.arrday DESC;";
}else if($period=="new"){
	$sql="select SR.nbadults, SR.nbkids, SR.arrday,SR.cost from reserv_swimp SR 
where SR.idhotel='$idhotel' and SR.arrday>=(CURRENT_DATE) ORDER BY SR.arrday DESC;";
}else if($period=="today"){
	$sql="select SR.nbadults, SR.nbkids, SR.arrday,SR.cost from reserv_swimp SR 
where SR.idhotel='$idhotel' and SR.arrday=(CURRENT_DATE);";
}else if($period==7){
	$sql="select SR.nbadults, SR.nbkids, SR.arrday,SR.cost from reserv_swimp SR 
where SR.idhotel='$idhotel' and ((CURRENT_DATE-INTERVAL 7 DAY)<=SR.arrday)and(SR.arrday<(CURRENT_DATE)) ORDER BY SR.arrday ASC;";
}else if($period==14){
	$sql="select SR.nbadults, SR.nbkids, SR.arrday,SR.cost from reserv_swimp SR 
where SR.idhotel='$idhotel' and ((CURRENT_DATE-INTERVAL 14 DAY)<=SR.arrday)and(SR.arrday<(CURRENT_DATE)) ORDER BY SR.arrday ASC;";
}else if($period==21){
	$sql="select SR.nbadults, SR.nbkids, SR.arrday,SR.cost from reserv_swimp SR 
where SR.idhotel='$idhotel' and ((CURRENT_DATE-INTERVAL 21 DAY)<=SR.arrday)and(SR.arrday<(CURRENT_DATE)) ORDER BY SR.arrday ASC;";
}else{
	$sql="select SR.nbadults, SR.nbkids, SR.arrday,SR.cost from reserv_swimp SR 
where SR.idhotel='$idhotel' and ((CURRENT_DATE-INTERVAL 31 DAY)<=SR.arrday)and(SR.arrday<(CURRENT_DATE)) ORDER BY SR.arrday ASC;";
}

$r = mysqli_query($conn,$sql);
if(!$r)
	echo mysqli_error($conn);
else{
$result=array();
 
 while($res = mysqli_fetch_array($r))
 array_push($result,array(
"col1"=>$res['nbadults'],"col2"=>$res['nbkids'],"col3"=>$res['arrday'],"col4"=>$res['cost']));
 echo json_encode(array("result"=>$result));
}

	}else{
if($period=="all"){
	 $sql="SELECT CR.nbpersons, CR.arrday, CR.cost from reserv_partyhall CR  
WHERE CR.idhotel='$idhotel' ORDER BY CR.arrday DESC;";	
}else if($period=="new"){
	 $sql="SELECT CR.nbpersons, CR.arrday, CR.cost from reserv_partyhall CR  
WHERE CR.idhotel='$idhotel' and (CR.arrday>=(CURRENT_DATE)) ORDER BY CR.arrday DESC;";
}else if($period=="today"){
	$sql="SELECT CR.nbpersons, CR.arrday, CR.cost from reserv_partyhall CR  
WHERE CR.idhotel='$idhotel' and (CR.arrday=(CURRENT_DATE));";
}else if($period==7){
	$sql="SELECT CR.nbpersons, CR.arrday, CR.cost from reserv_partyhall CR  
WHERE CR.idhotel='$idhotel' and ((CURRENT_DATE-INTERVAL 7 DAY)<=CR.arrday)and(CR.arrday<(CURRENT_DATE)) ORDER BY CR.arrday ASC;";
}else if($period==14){
	$sql="SELECT CR.nbpersons, CR.arrday, CR.cost from reserv_partyhall CR  
WHERE CR.idhotel='$idhotel' and ((CURRENT_DATE-INTERVAL 14 DAY)<=CR.arrday)and(CR.arrday<(CURRENT_DATE)) ORDER BY CR.arrday ASC;";
}else if($period==21){
	$sql="SELECT CR.nbpersons, CR.arrday, CR.cost from reserv_partyhall CR  
WHERE CR.idhotel='$idhotel' and ((CURRENT_DATE-INTERVAL 21 DAY)<=CR.arrday)and(CR.arrday<(CURRENT_DATE)) ORDER BY CR.arrday ASC;";
}else{
	$sql="SELECT CR.nbpersons, CR.arrday, CR.cost from reserv_partyhall CR  
WHERE CR.idhotel='$idhotel' and ((CURRENT_DATE-INTERVAL 31 DAY)<=CR.arrday)and(CR.arrday<(CURRENT_DATE)) ORDER BY CR.arrday ASC;";
}

$r = mysqli_query($conn,$sql);
if(!$r)
	echo mysqli_error($conn);
else{
$result=array();
 
 while($res = mysqli_fetch_array($r))
 array_push($result,array(
"col1"=>$res['nbpersons'],"col2"=>$res['arrday'],"col3"=>$res['cost']));
 echo json_encode(array("result"=>$result));

	}
	}	

	

	


mysqli_close($conn);
?>
