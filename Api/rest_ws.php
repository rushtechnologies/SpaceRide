<?PHP
$hostname_localhost = "localhost";
$database_localhost = "spaceride";
$username_localhost = "root";
$password_localhost = "n0m3l0";
$json = array();
$conexion = mysqli_connect($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);
$url_prueba = "http://192.168.30.101/aplicaciones/Android_Migration.php?caso=updateUserData&param1=&param2=&param3=&param4=&param5=&param6=&param7=&param8=&param9=";

if(isset($_GET["caso"])){
  $caso = $_GET["caso"];
  if($caso == "createUser"){
    $id = $_GET["param1"];
    $nombre = $_GET["param2"];
    $contra = $_GET["param3"];
    $correo = $_GET["param4"];
    $admin = $_GET["param5"];
    $sql = "call createUser('{$id}', '{$nombre}', '{$contra}', '{$correo}', '{$admin}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "loginUser"){
    $nombre = $_GET["param1"];
    $contra = $_GET["param2"];
    $sql = "call loginUser('{$nombre}', '{$contra}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1]);
      array_push($json, $resulta);
    }
  }else if($caso == "readUser"){
    $id = $_GET["param1"];
    $sql = "call readUser('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateUserData"){
    $nombre = $_GET["param1"];
    $id = $_GET["param2"];
    $correo = $_GET["param3"];
    $sql = "call updateUserData('{$id}','{$nombre}', '{$correo}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateUserPsd"){
    $id = $_GET["param1"];
    $contra = $_GET["param2"];
    $sql = "call updateUserPsd('{$id}','{$contra}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "readU_derrotasAndU_victorias"){
    $id = $_GET["param1"];
    $sql = "call readU_derrotasAndU_victorias('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1]);
      array_push($json, $resulta);
    }
  }else if($caso == "encryptidSR"){
    $id = $_GET["param1"];
    $sql = "call encryptidSR('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "decryptSR"){
    $encrypted = $_GET["param1"];
    $sql = "call decryptSR('{$encrypted}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "createPregunta"){
    $pregunta = $_GET["param1"];
    $respuesta1 = $_GET["param2"];
    $respuesta2 = $_GET["param3"];
    $respuesta3 = $_GET["param4"];
    $respuesta4 = $_GET["param5"];
    $correcta = $_GET["param6"];
    $dificultad = $_GET["param7"];
    $area = $_GET["param8"];
    $seleccion = $_GET["param9"];
    $sql = "call createPregunta('{$pregunta}', '{$respuesta1}','{$respuesta2}','{$respuesta3}','{$respuesta4}','{$correcta}','{$dificultad}','{$area}','{$seleccion}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "createFaq"){
    $pregunta = $_GET["param1"];
    $respuesta = $_GET["param2"];
    $tema = $_GET["param3"];
    $prioridad = $_GET["param4"];
    $aprobacion = $_GET["param5"];
    $usuario = $_GET["param6"];
    $reviso = $_GET["param7"];
    $sql = "call createFaq('{$pregunta}', '{$respuesta}','{$tema}','{$prioridad}','{$aprobacion}','{$usuario}','{$reviso}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "readFaq"){
    $id_Faq = $_GET["param1"];
    $sql = "call readFaq('{$id_Faq}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7]);
      array_push($json, $resulta);
    }
  }else if($caso == "readAllFaq_tema"){
    $sql = "call readAllFaq_tema()";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "readAllFaqwt"){
    $faq_tema = $_GET["param1"];
    $sql = "call readAllFaqwt('{$faq_tema}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7]);
      array_push($json, $resulta);
    }
  }else if($caso == "readAllFaq"){
    $sql = "call readAllFaq()";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7]);
      array_push($json, $resulta);
    }
  }else if($caso == "readAllFaqAdmin"){
    $sql = "call readAllFaqAdmin()";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7]);
      array_push($json, $resulta);
    }
  }else if($caso == "deleteFaq"){
    $faq_id = $_GET["param1"];
    $sql = "call deleteFaq('{$faq_id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateFaq_respuesta"){
    $faq_id = $_GET["param1"];
    $faq_res = $_GET["param2"];
    $faq_new_res = $_GET["param3"];
    $sql = "call updateFaq_respuesta('{$faq_id}', '{$faq_res}', '{$faq_new_res}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "createMuerto"){
    $id1 = $_GET["param1"];
    $id2 = $_GET["param2"];
    $area = $_GET["param3"];
    $dif = $_GET["param4"];
    $sql = "call createMuerto('{$id1}', '{$id2}', '{$area}', '{$dif}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateAndReadPregunta_seleccion"){
    $id_pregunta = $_GET["param1"];
    $sql = "call updateAndReadPregunta_seleccion('{$id_pregunta}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7],
    "variable9" => $row[8], "variable10" => $row[9]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateAndReadCustomPregunta_seleccion"){
    $dif = $_GET["param1"];
    $area = $_GET["param2"];
    $sql = "call updateAndReadCustomPregunta_seleccion('{$area}', '{$dif}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7],
    "variable9" => $row[8], "variable10" => $row[9]);
      array_push($json, $resulta);
    }
  }else if($caso == "readRandomPregunta"){
    $dif = $_GET["param1"];
    $area = $_GET["param2"];
    $sql = "call readRandomPregunta('{$area}', '{$dif}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7],
    "variable9" => $row[8], "variable10" => $row[9]);
      array_push($json, $resulta);
    }
  }else if($caso == "updatePregunta_seleccion"){
    $id = $_GET["param1"];
    $sql = "call updatePregunta_seleccion('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateResetP_seleccion"){
    $sql = "call updateResetP_seleccion()";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateU_muerto"){
    $id = $_GET["param1"];
    $sql = "call updateU_muerto('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "readU_muerto"){
    $sql = "call readU_muerto()";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateMuerto"){
    $id1 = $_GET["param1"];
    $id2 = $_GET["param2"];
    $area = $_GET["param3"];
    $dif = $_GET["param4"];
    $old = $_GET["param5"];
    $sql = "call updateMuerto('{$id1}', '{$id2}','{$area}','{$dif}','{$old}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateM_areaAndM_dif"){
    $id1 = $_GET["param1"];
    $area = $_GET["param2"];
    $dif = $_GET["param3"];
    $sql = "call updateM_areaAndM_dif('{$id1}','{$area}','{$dif}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "readMuerto"){
    $sql = "call readMuerto()";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateU_victorias"){
    $id = $_GET["param1"];
    $sql = "call updateU_victorias('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateU_derrotas"){
    $id = $_GET["param1"];
    $sql = "call updateU_derrotas('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateFirstU_derrotas"){
    $areaid = $_GET["param1"];
    $areamax = $_GET["param2"];
    $sql = "call updateFirstU_derrotas('{$areaid}', '{$areamax}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "readArea_max"){
    $areaid = $_GET["param1"];
    $sql = "call readArea_max('{$areaid}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateU_muertoReset"){
    $id1 = $_GET["param1"];
    $id2 = $_GET["param2"];
    $sql = "call updateU_muertoReset('{$id1}', '{$id2}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_mate"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_mate('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_fis"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_fis('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_esp"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_esp('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_huni"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_huni('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_geo"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_geo('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_ing"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_ing('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_qui"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_qui('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_bio"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_bio('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_hmex"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_hmex('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_astro"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_astro('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_ent"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_ent('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_arte"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_arte('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateArea_tec"){
    $area1 = $_GET["param1"];
    $area2 = $_GET["param2"];
    $area3 = $_GET["param3"];
    $sql = "call updateArea_tec('{$area1}', '{$area2}', '{$area3}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "readArea"){
    $area1 = $_GET["param1"];
    $sql = "call readArea('{$area1}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7],
    "variable9" => $row[8], "variable10" => $row[9], "variable11" => $row[10], "variable12" => $row[11], "variable13" => $row[12],
    "variable14" => $row[13], "variable15" => $row[14], "variable16" => $row[15]);
      array_push($json, $resulta);
    }
  }else if($caso == "readM_correcta"){
    $area1 = $_GET["param1"];
    $sql = "call readM_correcta('{$area1}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateM_correcta"){
    $id = $_GET["param1"];
    $correcta = $_GET["param2"];
    $sql = "call updateM_correcta('{$id}', '{$correcta}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "readAllChat"){
    $s_id = $_GET["param1"];
    $u_id = $_GET["param2"];
    $sql = "call readAllChat('{$s_id}', '{$u_id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6]);
      array_push($json, $resulta);
    }
  }else if($caso == "readSAllChats"){
    $s_id = $_GET["param1"];
    $sql = "call readSAllChats('{$s_id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6]);
      array_push($json, $resulta);
    }
  }else if($caso == "createChat"){
    $s_id = $_GET["param1"];
    $u_id = $_GET["param2"];
    $sql = "call createChat('{$s_id}', '{$u_id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateChat_S_Msgs"){
    $s_id = $_GET["param1"];
    $u_id = $_GET["param2"];
    $s_msgs = $_GET["param3"];
    $sql = "call updateChat_S_Msgs('{$s_id}', '{$u_id}', '{$s_msgs}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateChat_U_Msgs"){
    $s_id = $_GET["param1"];
    $u_id = $_GET["param2"];
    $s_msgs = $_GET["param3"];
    $sql = "call updateChat_U_Msgs('{$s_id}', '{$u_id}', '{$s_msgs}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "readChatTime"){
    $id = $_GET["param1"];
    $sql = "call readChatTime('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateChatTime"){
    $id = $_GET["param1"];
    $time = $_GET["param2"];
    $sql = "call updateChatTime('{$id}', '{$time}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "createEventReport"){
    $u_id= $_GET["param1"];
    $transcript= $_GET["param2"];
    $description= $_GET["param3"];
    $category= $_GET["param4"];
    $s_id= $_GET["param5"];
    $sql = "call updateChatTime('{$u_id}', '{$transcript}', '{$description}', '{$category}', '{$s_id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "readERUser"){
    $id = $_GET["param1"];
    $sql = "call readERUser('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7],
    "variable9" => $row[8]);
      array_push($json, $resulta);
    }
  }else if($caso == "readERMaintenance"){
    $sql = "call readERMaintenance()";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7],
    "variable9" => $row[8]);
      array_push($json, $resulta);
    }
  }else if($caso == "readEventReport"){
    $id = $_GET["param1"];
    $sql = "call readEventReport('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7],
    "variable9" => $row[8]);
      array_push($json, $resulta);
    }
  }else if($caso == "readERSupport"){
    $id = $_GET["param1"];
    $sql = "call readERSupport('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7],
    "variable9" => $row[8]);
      array_push($json, $resulta);
    }
  }else if($caso == "readAllER"){
    $sql = "call readAllER()";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6], "variable8" => $row[7],
    "variable9" => $row[8]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateERSupport"){
    $id = $_GET["param1"];
    $solution = $_GET["param2"];
    $sql = "call updateERSupport('{$id}', '{$solution}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateERChief"){
    $id = $_GET["param1"];
    $s_id = $_GET["param2"];
    $sql = "call updateERChief('{$id}', '{$s_id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateERMaintenance"){
    $id = c["param1"];
    $sql = "call updateERMaintenance('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateERUser"){
    $id = $_GET["param1"];
    $status = $_GET["param2"];
    $sql = "call updateERUser('{$id}', '{$status}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "deleteEventReport"){
    $id = $_GET["param1"];
    $sql = "call deleteEventReport('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "createMaintenanceReport"){
    $module = $_GET["param1"];
    $description = $_GET["param2"];
    $s_id = $_GET["param3"];
    $sql = "call createMaintenanceReport('{$module}', '{$description}', '{$s_id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "readMaintenanceReport"){
    $id = $_GET["param1"];
    $sql = "call readMaintenanceReport('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6]);
      array_push($json, $resulta);
    }
  }else if($caso == "readDeveloperMR"){
    $id = $_GET["param1"];
    $sql = "call readDeveloperMR('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6]);
      array_push($json, $resulta);
    }
  }else if($caso == "readAllMR"){
    $sql = "call readAllMR()";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6]);
      array_push($json, $resulta);
    }
  }else if($caso == "readAllMRList"){
    $sql = "call readAllMRList()";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateMRDeveloper"){
    $id = $_GET["param1"];
    $solution = $_GET["param2"];
    $sql = "call updateMRDeveloper('{$id}', '{$solution}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "updateMRChief"){
    $id = $_GET["param1"];
    $s_id = $_GET["param2"];
    $sql = "call updateMRChief('{$id}', '{$s_id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "deleteMaintenanceReport"){
    $id = $_GET["param1"];
    $sql = "call deleteMaintenanceReport('{$id}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "Altas"){
    $modulo = $_GET["param1"];
    $desc = $_GET["param2"];
    $stat = $_GET["param3"];
    $solucion = $_GET["param4"];
    $mrsid = $_GET["param5"];
    $fecha = $_GET["param6"];
    $sql = "call Altas('{$modulo}', '{$desc}', '{$stat}', '{$solucion}', '{$mrsid}', '{$fecha}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "RequestTodo"){
    $sql = "call RequestTodo()";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6]);
      array_push($json, $resulta);
    }
  }else if($caso == "RequestEspecifico"){
    $folio = $_GET["param1"];
    $sql = "call RequestEspecifico('{$folio}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6]);
      array_push($json, $resulta);
    }
  }else if($caso == "RequestDesarrollador"){
    $idres = $_GET["param1"];
    $sql = "call RequestDesarrollador('{$idres}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0],"variable2" => $row[1],"variable3" => $row[2],"variable4" => $row[3],"variable5" => $row[4],"variable6" => $row[5],"variable7" => $row[6]);
      array_push($json, $resulta);
    }
  }else if($caso == "Actualizar"){
    $fecha = $_GET["param1"];
    $decripcion = $_GET["param2"];
    $solucion = $_GET["param3"];
    $stat = $_GET["param4"];
    $folio = $_GET["param5"];
    $sql = "call Actualizar('{$fecha}', '{$descripcion}', '{$solucion}', '{$stat}', '{$folio}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }else if($caso == "Borrar"){
    $folio = $_GET["param1"];
    $sql = "call Borrar('{$folio}')";
    $result = mysqli_query($conexion, $sql) or die("Query fail: " . mysqli_error($conexion));
    while ($row = mysqli_fetch_array($result)){
      $resulta = array("variable1" => $row[0]);
      array_push($json, $resulta);
    }
  }
}else{
  $resulta = array("No hay caso", "Error");
  $json = $resulta;
}

mysqli_close($conexion);
echo json_encode($json);
?>
