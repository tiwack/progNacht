<?php
$con=mysqli_connect("localhost","root","");
$sql="CREATE DATABASE geoUser";
if (mysqli_query($con,$sql))
{
   echo "Database my_db created successfully";
}
$con=mysqli_connect("localhost","root","","geoUser");
$sql2="CREATE TABLE User(Usermail CHAR(30),PRIMARY KEY(Usermail),PasswordHash CHAR(30))";
if (mysqli_query($con,$sql2))
{
   echo "Table have been created successfully";
}
?>