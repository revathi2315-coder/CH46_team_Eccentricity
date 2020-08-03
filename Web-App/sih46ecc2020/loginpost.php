<?php
 = $_REQUEST["user_id"];
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
	
// Check whether username or password is set from android	
     if(isset($_POST['username'] ) && isset($_POST['password']))
     {
	$user_id = $_POST['username'];
        $user_password = $_POST['password'];
	$user_email = $_POST['useremail'];
	$user_givenname = $_POST['usergivenname'];
	$user_agegroup = $_POST['useragegroup'];
	$user_insuranceplan = $_POST['userinsuranceplan'];
	$user_heartscore = $_POST['heartscore'];
	$user_email = $_POST['useremail'];
	$user_loggeddate = date("Y-m-d H:i:s");

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
        		// echo "SUCCESS";
		// Now update the Database with the other fields
		
                $sqlinsert = "INSERT INTO `user_data`(`user_givenname`, `user_email`, `user_healthscore`, `user_loggeddate`, `user_agegroup`, `user_insuranceplan`)
		VALUES('$user_givenname','$user_email','$user_heartscore','$user_loggeddate','$user_agegroup','$user_insuranceplan')";
                       if (mysqli_query($db, $sqlinsert)) {
                               echo "Success";
                       }
                       else {
                         echo "Error: " . $sql . "<br>" . mysqli_error($conn);
                       }

                  mysqli_close($conn); 
		
	 }
	else
	{
         echo "Failure";
	}
    }
    else
	{
	 echo "Failure;
	}
      	
exit();
?>
