<?php
require_once('conn.php');
require_once('PHPMailer_5.2.4/class.phpmailer.php');
//include("class.smtp.php"); // optional, gets called from within class.phpmailer.php if not already loaded

function generateRandomString($length = 10) {
    $characters = '0123456789';//abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    $charactersLength = strlen($characters);
    $randomString = '';
    for ($i = 0; $i < $length; $i++) {
        $randomString .= $characters[rand(0, $charactersLength - 1)];
    }
    return $randomString;
}

$email=$_POST["recov_mail"];
//$email="tesviry@yahoo.com";

$sql_query = "select count(*) from users where email like '$email';";
$result = mysqli_query($conn,$sql_query);  
$count=mysqli_fetch_assoc($result);
if( $count["count(*)"]>0 ) {

$code=generateRandomString(8);


$mail = new PHPMailer(true); // the true param means it will throw exceptions on errors, which we need to catch

$mail->IsSMTP(); // telling the class to use SMTP

try {
  $mail->Host       = "smtp.yandex.com"; // SMTP server
  $mail->SMTPDebug  = 1;                     //2 enables SMTP debug information (for testing)
  $mail->SMTPAuth   = true;                  // enable SMTP authentication
  $mail->SMTPSecure = "ssl";                  // enable SMTP authentication

  $mail->Port       = 465;                    // set the SMTP port for the GMAIL server
  $mail->Username   = "hotelsbookingsteam@yandex.com"; // SMTP account username
  $mail->Password   = "electroman@02";        // SMTP account password
  
  $mail->From = "hotelsbookingsteam@yandex.com";
  $mail->Username = "hotelsbookingsteam@yandex.com";
  $mail->AddAddress( $email, '');
  $mail->SetFrom('hotelsbookingsteam@yandex.com', 'Hotel Bookings Team');

  $mail->Subject = 'HotelsBookings app password reset code';
  $mail->Body="Hello

  We've sent this message because you (or someone else) entered this email address to change the password of your HotelsBookings app account. 

  To continue the password reset process, please use this reset code ".$code;

  $mail->Send();
 
  echo "Code Sent successfully\n";
  
} catch (phpmailerException $e) {
  echo $e->errorMessage(); //Pretty error messages from PHPMailer
} catch (Exception $e) {
  echo $e->getMessage(); //Boring error messages from anything else!
}

$dateTimeVariable = date("Y-m-d H:i:s");
 $sql_query = "select count(*) from recoverycodes where mail like '$email';";  
 $result = mysqli_query($conn,$sql_query);  
 $count=mysqli_fetch_assoc($result);
if( $count["count(*)"]>0 ) {  
	$sql_query = "update recoverycodes set recovcode = '$code', senttime='$dateTimeVariable' where mail like '$email';"; 
	if(!mysqli_query($conn,$sql_query)){echo "Error while updating code".mysqli_error($conn);}	
 }  
 else  
 {   
 $sql_query="insert into recoverycodes values('$email','$code','$dateTimeVariable');"; 
 if(!mysqli_query($conn,$sql_query)){echo "Error while insertion".mysqli_error($conn);}
 } 
 }else
	 echo "Sorry, this account doesn't exist";
 mysqli_close($conn);
?>