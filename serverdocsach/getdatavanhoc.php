<?php
	$connect = mysqli_connect("localhost","root","","book");
	mysqli_query($connect,"SET NAMES 'utf8'");
	$idsach = 1;
	$mangsach = array();
	$query = "SELECT * FROM sach WHERE idsach = $idsach";
	$datas = mysqli_query($connect,$query);
	while ($row = mysqli_fetch_assoc($datas)) {
		array_push($mangsach, new Bookvanhoc(
			$row['id'],
			$row['tensach'],
			$row['tacgiasach'],
			$row['hinhanhsach'],
			$row['idsach']));
	}


	
	echo json_encode($mangsach);
	class Bookvanhoc{
		function Bookvanhoc($id, $tensach, $tacgiasach, $hinhanhsach, $idsach){
			$this ->id = $id;
			$this ->tensach = $tensach;
			$this ->tacgiasach = $tacgiasach;
			$this ->hinhanhsach = $hinhanhsach;
			$this ->idsach = $idsach;
		}
	}
?>