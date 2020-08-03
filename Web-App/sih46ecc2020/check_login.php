<?php
$user_id = $_REQUEST["user_id"];
$user_password = $_REQUEST["user_secret"];

 $host="localhost:3308";
 $username="root";
 $password="";
 $databasename="sih_ch46_eccentricity";
 $db = mysqli_connect($host,$username,$password, $databasename);

 
if ($db -> connect_errno) {
  echo "Database Issue Please Try Again after Sometime" ;
  exit();
}
$sql = "select user_id from user_details where user_id='$user_id' and user_password='$user_password'";
	  $result = mysqli_query($db,$sql);
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
      $active = $row['user_id'];
      $count = mysqli_num_rows($result);
	  if(strcmp($user_id, '') == 0 || strcmp($user_password, '') == 0){
		   echo "Your Login Name or Password is invalid";
	  }
	 else if(strcmp($user_id, $active) == 0 )
	 {
         echo "SUCCESS";
	 }
	else
	{
         echo "Your Login Name or Password is invalid";
    }
exit();
?>
