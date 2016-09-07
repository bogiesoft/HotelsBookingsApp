<?php
require_once('conn.php');
$id=$_POST["hotelid"];
$name=$_POST["hname"];
$rate=$_POST["hrate"];
$tel=$_POST["htel"];
$webs=$_POST["hwebs"];
$restau=$_POST["hrestau"];
$park=$_POST["hpark"];
$swimp=$_POST["hswip"];
$clbh=$_POST["hclbh"];
$wifi=$_POST["wifi"];
$gym=$_POST["hgym"];
$gamr=$_POST["hgamr"];
$hlco=$_POST["hhlco"];
$nighp=$_POST["hnighip"];
$restaup=$_POST["hrestaup"];
$swimpp=$_POST["hswimpp"];
$celbhp=$_POST["hcelebhp"];
$sql_query="update hotels set name='$name',rating='$rate',tel='$tel',website='$webs',
parking='$park',restaurent='$restau',swimming_pool='$swimp',celebration_hall='$clbh',
wifi='$wifi',gym='$gym',games_room='$gamr',helicopter_landing ='$hlco',night_price='$nighp',
restau_table_price='$restaup',swimp_price='$swimpp',party_hall_price='$celbhp' where id = '$id';";

if($res=mysqli_query($conn,$sql_query))
	echo "Hotel updated successfully";
else
	echo "Hotel not Updated please retry";
mysqli_close($conn);

?>