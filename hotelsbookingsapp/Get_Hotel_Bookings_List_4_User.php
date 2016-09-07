<?php
require_once('conn.php');
$iduser=$_POST["iduser"];
$serv=$_POST["serv"];
if($serv=="rooms"){
	$sql="select HT.name, RR.numroom,RR.nbadults,RR.nbkids,RR.arrday,RR.days,RR.daysleft,RR.cost from hotels HT,
reserv_room RR WHERE  RR.iduser='$iduser' and  HT.id=RR.idhotel and CURRENT_DATE <= (RR.arrday + INTERVAL RR.days DAY);";

$r = mysqli_query($conn,$sql);
if(!$r)
	echo mysqli_error($conn);
else{
$result=array();
 
 while($res = mysqli_fetch_array($r))
 array_push($result,array(
"col1"=>$res['name'],"col2"=>$res['numroom'],"col3"=>$res['nbadults'],"col4"=>$res['nbkids'],
"col5"=>$res['arrday'],"col6"=>$res['days'],"col7"=>$res['daysleft'],"col8"=>$res['cost'])
);
 echo json_encode(array("result"=>$result));
}

}else if($serv=="restau"){
	$sql="SELECT HT.name, RR.nbpers, RR.arrtime, RR.cost from hotels HT, reserv_restau RR 
where RR.iduser='$iduser' and RR.idhotel=HT.id and (CURRENT_TIMESTAMP<=(RR.arrtime+INTERVAL 12 HOUR));";

$r = mysqli_query($conn,$sql);
if(!$r)
	echo mysqli_error($conn);
else{
$result=array();
 
 while($res = mysqli_fetch_array($r))
 array_push($result,array(
"col1"=>$res['name'],"col2"=>$res['nbpers'],"col3"=>$res['arrtime'],"col4"=>$res['cost']));
 echo json_encode(array("result"=>$result));
}

}else if($serv=="swimp"){
	$sql="select HT.name, SR.nbadults, SR.nbkids, SR.arrday,SR.cost from hotels HT, reserv_swimp SR 
where SR.iduser='$iduser' and HT.id=SR.idhotel and SR.arrday>=(CURRENT_DATE);";

$r = mysqli_query($conn,$sql);
if(!$r)
	echo mysqli_error($conn);
else{
$result=array();
 
 while($res = mysqli_fetch_array($r))
 array_push($result,array(
"col1"=>$res['name'],"col2"=>$res['nbadults'],"col3"=>$res['nbkids'],"col4"=>$res['arrday'],"col5"=>$res['cost']));
 echo json_encode(array("result"=>$result));
}

}else{
	$sql="SELECT HT.name, CR.nbpersons, CR.arrday, CR.cost from hotels HT, reserv_partyhall CR  
WHERE CR.iduser='$iduser' AND HT.id=CR.idhotel and (CR.arrday>=(CURRENT_DATE))";


$r = mysqli_query($conn,$sql);
if(!$r)
	echo mysqli_error($conn);
else{
$result=array();
 
 while($res = mysqli_fetch_array($r))
 array_push($result,array(
"col1"=>$res['name'],"col2"=>$res['nbpersons'],"col3"=>$res['arrday'],"col4"=>$res['cost']));
 echo json_encode(array("result"=>$result));

	}
}
mysqli_close($conn);
?>