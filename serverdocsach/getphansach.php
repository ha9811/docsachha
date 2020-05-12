<?php
	$connect = mysqli_connect("localhost","root","","book");
	mysqli_query($connect,"SET NAMES 'utf8'");
	$idsach = 1;
	$mangphan = array();
	$query = "SELECT * FROM phansach WHERE idsach = $idsach";
	$datas = mysqli_query($connect,$query);
	while ($row = mysqli_fetch_assoc($datas)) {
		array_push($mangphan, new Phansach(
			$row['id'],
			$row['tenphan'],
			$row['text'],
			$row['idsach']));
	}
	echo json_encode($mangphan);
	class Phansach{
		function Phansach($id, $tenphan, $text, $idsach){
			$this ->id = $id;
			$this ->tenphan = $tenphan;
			$this ->text = $text;
			$this ->idsach = $idsach;
		}
	}
?>