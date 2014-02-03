<?php
$con=mysqli_connect("localhost","root","","geoUser");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$username = $_GET['mail'];
$password = $_GET['password'];

$sql = "insert into User VALUES('$username' , '$password')";
if(mysqli_query($con,$sql))
{
   echo "erfolg";
}else
{
	echo "fail";
}
mysqli_close($con);
?>

