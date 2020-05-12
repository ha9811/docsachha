<?php
$connect = mysqli_connect("localhost","root","","book");
	mysqli_query($connect,"SET NAMES 'utf8'");
$query = "SELECT * FROM sach ORDER BY ID LIMIT 6";
$datas = mysqli_query($connect,$query);
$mangsach = array();
while ($row = mysqli_fetch_assoc($datas)) {
		array_push($mangsach, new Bookhot(
			$row['id'],
			$row['tensach'],
			$row['tacgiasach'],
			$row['hinhanhsach'],
			$row['idsach']));
	}
	echo json_encode($mangsach);
class Bookhot{
		function Bookhot($id, $tensach, $tacgiasach, $hinhanhsach, $idsach){
			$this ->id = $id;
			$this ->tensach = $tensach;
			$this ->tacgiasach = $tacgiasach;
			$this ->hinhanhsach = $hinhanhsach;
			$this ->idsach = $idsach;
		}
	}



?>