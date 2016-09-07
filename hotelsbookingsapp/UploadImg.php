<?php
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 $image = $_POST['image'];
                $name = $_POST['name'];
				$hotel=$_POST['idhotel'];
 require_once('conn.php');
 $dbname=$name.'.png';
 $sql ="INSERT INTO hotels_images VALUES ('$hotel','$dbname','0');";
 
 
 if(mysqli_query($conn,$sql)){
 

 $path = "IMG_Hotels/$name.png";
 
 $actualpath = $path;
 

 file_put_contents($path,base64_decode($image));
 echo "Successfully Uploaded";


 mysqli_close($conn);
  }else{
 echo "Error while uploading";
 }}else{
 echo "Error";
 }
 ?>