<?php
$dateiname = "nachrichten.json";

$json = $_POST['json'];
$datei = file_get_contents($dateiname);
$datei = substr($datei, 0, -2);
$datei = $datei .",". $json . "]}";

$dateiNeu = fopen($dateiname,"w");
fwrite($dateiNeu, $datei);
fclose($dateiNeu);


?>