<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/social.css">

<script type="text/javascript">
function check_login()
{
	 var userid = document.getElementById("user_id").value;
	 var usersecret = document.getElementById("user_secret").value;
	
	 	var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200)
			{
					if(this.responseText == "SUCCESS")
					{
						window.location.href="healthscoreanalyser.php";
					}
					else
					{
						document.getElementById("txtHint").innerHTML = this.responseText;
					}
		   }
		};
    xmlhttp.open("GET", "check_login.php?user_id="+userid+"&user_secret="+usersecret, true);
    xmlhttp.send();
}
</script>
    </head>
    <!--border: 2px solid #dd4b39; -->
    <body class="image">
    <div class="container">
 <div class= "h1">SAIL</div>
<div class="float-child">
  <video width="300" height="200" autoplay="autoplay"  loop>
  <source src="video/sail.mp4" type="video/mp4" />
</video>
    
</div>
        <div class="float-child-right">
<p>Login</p>
<form  id="check_login" >
<input type="text" placeholder="Enter username" id="user_id" required>
<input type="password" placeholder="Enter Password" id="user_secret" required>
<div >
    <button class="loginbtn" type="button" onclick="check_login()">Login</button>
	<div id="txtHint"></div>
</form>
</div>
            </div>
      <div class="footer">
          <p>SIH CH46 ECCENTRICITY © 2020. All rights reserved.</p>
      </div>
        </div>
		
      
</body>
</html>