<?php

$addr = $_REQUEST["addr"];
$city = $_REQUEST["city"];
$state = $_REQUEST["state"];
$temp = $_REQUEST["temp"];

$geo = "https://maps.googleapis.com/maps/api/geocode/xml?address=";
$address = str_replace (" ", "+", $addr);
$gkey = "AIzaSyD7i30JO9TJd1j6oJbnZkNdWHe8nsxzICk";
$geourl = $geo . $address . ","  . $city . "," . $state . "&key=" . $gkey;

$xml = simplexml_load_file($geourl) or die("url not loading");

if ($xml->status == "ZERO_RESULTS") {
    echo '<script language="javascript">';
    echo 'alert("Sorry, We can not find this address!")';
    echo '</script>';
}

else {
    $lat = $xml->result->geometry->location->lat;
    $lng = $xml->result->geometry->location->lng;
    $fkey = "https://api.forecast.io/forecast/45b34821a054443a5617dfb68c1e48c2/";
    $jsonurl = $fkey . $lat . "," . $lng . "?units=" . $temp . "&exclude=flags";
    $jsonfile = file_get_contents($jsonurl);
    //$jtag = json_decode($jsonfile, true);
}
//test code
//echo $geourl . "<br>" . $jsonurl . "<br>" . $jsonfile;
echo $jsonfile;

?>