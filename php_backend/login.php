<?php
$con=mysqli_connect("localhost","root","","geoUser");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$username = $_GET['mail'];
$password = $_GET['password'];

$sql = "select PasswordHash from User where Usermail = '$username'";
$result = mysqli_query($con,$sql);
$row = mysqli_fetch_array($result);
$dbHash = $row[0];

if($dbHash == $password)
{
   echo "erfolg";
}else
{
	echo "fail";
}
mysqli_close($con);
?>
