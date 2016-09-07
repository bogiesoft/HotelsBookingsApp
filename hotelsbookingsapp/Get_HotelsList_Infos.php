<?php
require_once('conn.php');
require_once('test1room.php');
require_once('test2room.php');
require_once('test3room.php');
require_once('test4room.php');
require_once('test5room.php');
$region=$_POST["reg"];
$service=$_POST["serv"];
$arrday=$_POST["arrday"];
$days=$_POST["nbdays"];
$sum1=$_POST["sum1"];
$sum2=$_POST["sum2"];
$sum3=$_POST["sum3"];
$sum4=$_POST["sum4"];
$sum5=$_POST["sum5"];

$result=array();
function sortByRating($a, $b) {
    return $a['rating'] > $b['rating'];
}
if($service=="rooms"){
	$a=array();
	$sql="select id from hotels where region like '$region';";
	if($r=mysqli_query($conn,$sql)){
	while($res=mysqli_fetch_assoc($r)){
	if($sum1!="none"&&$sum2!="none"&&$sum3!="none"&&$sum4!="none"&&$sum5!="none"){
	       if(test5room($res['id'],$sum1,$sum2,$sum3,$sum4,$sum5,$arrday,$days))
			$a=array_merge($a,array_fill(0,1,$res["id"]));
	}else 
		if($sum1!="none"&&$sum2!="none"&&$sum3!="none"&&$sum4!="none"){
			if(test4room($res['id'],$sum1,$sum2,$sum3,$sum4,$arrday,$days))
			$a=array_merge($a,array_fill(0,1,$res["id"]));
	}else if($sum1!="none"&&$sum2!="none"&&$sum3!="none"){
			if(test3room($res['id'],$sum1,$sum2,$sum3,$arrday,$days))
			$a=array_merge($a,array_fill(0,1,$res["id"]));
	}else if($sum1!="none"&&$sum2!="none"){
			if(test2room($res['id'],$sum1,$sum2,$arrday,$days))
			$a=array_merge($a,array_fill(0,1,$res["id"]));
	}else{
			if(test1room($res['id'],$sum1,$arrday,$days))
			$a=array_merge($a,array_fill(0,1,$res["id"]));
	}
	}
	
	$max = sizeof($a);
	for($i = 0; $i < $max;$i++){
	$tmp=$a[$i];
	//echo "tmp= ".$tmp.'<br>';
	$sql1="select HT.id, HT.name As hotelname, HT.rating, HT.night_price As price,HI.name As imgname from hotels HT, hotels_images HI 
	where HT.id='$tmp' and HT.region like '$region'and HI.idhotel=HT.id and HI.ismain=1;";
		
	$sql2="select HT.id, HT.name As hotelname, HT.rating, HT.night_price As price from hotels HT
	where HT.id='$tmp' and HT.region like '$region' and HT.id NOT IN (
    select idhotel from hotels_images WHERE ismain=1
    );";
	
if($r = mysqli_query($conn,$sql1)){
while($res=mysqli_fetch_array($r))
array_push($result,array("idhotel" => $res["id"],
"hotelname"=>$res['hotelname'],"rating"=>$res["rating"],
"price"=>$res['price'],"imgname"=>$res['imgname']));
}

if($r = mysqli_query($conn,$sql2)){
while($res=mysqli_fetch_array($r))
array_push($result,array("idhotel" => $res["id"],
"hotelname"=>$res['hotelname'],"rating"=>$res["rating"],
"price"=>$res['price'],"imgname"=>"none"));
}
	
}
}}else if($service=="restau"){
	$sql1="select HT.id, HT.name As hotelname, HT.rating, HT.restau_table_price As price,HI.name As imgname from hotels HT, hotels_images HI 
	where HT.region like '$region' and HT.restaurent like 'yes' and HI.idhotel=HT.id and HI.ismain=1;";
	
	$sql2="select HT.id, HT.name As hotelname, HT.rating, HT.restau_table_price As price from hotels HT 
	where HT.region like '$region' and HT.restaurent like 'yes' and HT.id NOT IN (
    select idhotel from hotels_images WHERE ismain=1
    );";

if($r = mysqli_query($conn,$sql1)){
while($res=mysqli_fetch_array($r))
array_push($result,array("idhotel" => $res["id"],
"hotelname"=>$res['hotelname'],"rating"=>$res["rating"],
"price"=>$res['price'],"imgname"=>$res['imgname']));
}

if($r = mysqli_query($conn,$sql2)){
while($res=mysqli_fetch_array($r))
array_push($result,array("idhotel" => $res["id"],
"hotelname"=>$res['hotelname'],"rating"=>$res["rating"],
"price"=>$res['price'],"imgname"=>"none"));
}
	}else if($service=="swimp"){
	$sql1="select HT.id, HT.name As hotelname, HT.rating, HT.swimp_price As price,HI.name As imgname from hotels HT, hotels_images HI 
	where HT.region like '$region' and HT.swimming_pool like 'yes' and HI.idhotel=HT.id and HI.ismain=1;";
	
	$sql2="select HT.id, HT.name As hotelname, HT.rating, HT.swimp_price As price from hotels HT 
	where HT.region like '$region' and HT.swimming_pool like 'yes' and HT.id NOT IN (
    select idhotel from hotels_images WHERE ismain=1
    )";

	

if($r = mysqli_query($conn,$sql1)){
while($res=mysqli_fetch_array($r))
array_push($result,array("idhotel" => $res["id"],
"hotelname"=>$res['hotelname'],"rating"=>$res["rating"],
"price"=>$res['price'],"imgname"=>$res['imgname']));
}

if($r = mysqli_query($conn,$sql2)){
while($res=mysqli_fetch_array($r))
array_push($result,array("idhotel" => $res["id"],
"hotelname"=>$res['hotelname'],"rating"=>$res["rating"],
"price"=>$res['price'],"imgname"=>"none"));
	}
	}else{
	$sql1="select HT.id, HT.name As hotelname, HT.rating, HT.party_hall_price As price,HI.name As imgname from hotels HT, hotels_images HI 
	where HT.region like '$region' and HT.celebration_hall like 'yes' and HI.idhotel=HT.id and HI.ismain=1
	and HT.id not in(
    select idhotel from reserv_partyhall where arrday='$arrday'
    )
	;";
	
	$sql2="select HT.id, HT.name As hotelname, HT.rating, HT.party_hall_price As price from hotels HT 
	where HT.region like '$region' and HT.celebration_hall like 'yes' and HT.id NOT IN (
    select idhotel from hotels_images WHERE ismain=1)
	and HT.id not in(
    select idhotel from reserv_partyhall where arrday='$arrday'
    );";
	
if($r = mysqli_query($conn,$sql1)){
while($res=mysqli_fetch_array($r))
array_push($result,array("idhotel" => $res["id"],
"hotelname"=>$res['hotelname'],"rating"=>$res["rating"],
"price"=>$res['price'],"imgname"=>$res['imgname']));
}

if($r = mysqli_query($conn,$sql2)){
while($res=mysqli_fetch_array($r))
array_push($result,array("idhotel" => $res["id"],
"hotelname"=>$res['hotelname'],"rating"=>$res["rating"],
"price"=>$res['price'],"imgname"=>"none"));
}
}


if($result!=NULL){	
usort($result, 'sortByRating');
echo json_encode(array("result"=>$result));
}else
	echo "No data found !";
mysqli_close($conn);
?>