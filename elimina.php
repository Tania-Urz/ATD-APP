<?php
    include "BD_php.php";
    if(isset($_GET['Nombre']))
    {
        $Nombre=$_GET['Nombre'];
        $bd = new BaseDeDatos();
        $respuesta = $bd->eliminar($Nombre);
        echo '{"usr":'.$respuesta.'}';
    }
?>