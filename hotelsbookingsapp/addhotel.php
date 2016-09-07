<?php
require_once('conn.php');
$adminid = $_POST["adminid"];
$addh_region =$_POST["addh_region"];
$addh_rating = $_POST["addh_rating"];
$addh_name =$_POST["addh_name"];
$addh_tel = $_POST["addh_tel"];
$addh_website = $_POST["addh_website"];
$addh_restau=$_POST["addh_restaurent"];
$addh_parking = $_POST["addh_parking"];
$addh_swimmingpool = $_POST["addh_swimmingpool"];
$addh_celbrationhall = $_POST["addh_celbrationhall"];
$addh_wifi=$_POST["addh_wifi"];
$addh_gym = $_POST["addh_gym"];
$addh_gamesroom = $_POST["addh_gamesroom"];
$addh_helicopter = $_POST["addh_helicopter"];
$addh_nightp=$_POST["addh_nightp"];
$addh_restautp=$_POST["addh_restautp"];
$addh_swimpp=$_POST["addh_swimpp"];
$addh_partyhp=$_POST["addh_partyhp"];

$sql_query="select count(*) from hotels where region like '$addh_region' and name like '$addh_name';";
$res=mysqli_query($conn,$sql_query);
$nb=mysqli_fetch_assoc($res);
if($nb['count(*)']==0){
$sql_query ="insert into hotels values (null,'$adminid','$addh_region','$addh_rating','$addh_name','$addh_tel',
'$addh_website','$addh_restau','$addh_parking','$addh_swimmingpool','$addh_celbrationhall','$addh_wifi','$addh_gym',
'$addh_gamesroom','$addh_helicopter','$addh_nightp','$addh_restautp','$addh_swimpp','$addh_partyhp');";

if(mysqli_query($conn,$sql_query))
	echo "Hotel added successfully";
else
	echo "Hotel not added please retry !";
}else
	echo "Hotel Already exists !";
mysqli_close($conn);
?>