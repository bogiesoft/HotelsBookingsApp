<?php
require_once('conn.php');
require_once('test5room.php');
require_once('test4room.php');
require_once('test3room.php');
require_once('test2room.php');
require_once('test1room.php');
/*
$sql="select id from hotels;";
$a=array();
if($r=mysqli_query($conn,$sql)){
	while($res=mysqli_fetch_array($r))
	$a=array_merge($a,array_fill(0,1,$res["id"]));

$max = sizeof($a);
for($i = 0; $i < $max;$i++)
{
echo $a[$i];
}
}*/
/*if(test3room(5,1,1,1,"2016-08-28",2))
	echo "yes";
else
	echo "no";
*//*
$a=1.5663;
$b=2.01;
$c=$a*$b;
echo $c;*/
/*
if(test5room(1,1,1,1,1,1,"2016-08-30",1))
	echo "ok";*/
/*
$idhotel=1;
$sql="select night_price as uprice from hotels where id = '$idhotel';";
if($res=mysqli_query($conn,$sql))
	$uprice=mysqli_fetch_assoc($res);
echo $uprice['uprice'];
*/
$email="riadh.bh@yandex.com";
$sql_query = "select count(*) from users where email like '$email';";
$result = mysqli_query($conn,$sql_query);  
 $count=mysqli_fetch_assoc($result);
if( $count["count(*)"]>0 ) {
echo "ok";
}
else echo "no";
mysqli_close($conn);
?>