<?php
    include "BD_php.php";

    if(isset($_GET['Nombre']) && isset($_GET['fecha_Na']) && isset($_GET['escuela']) && isset($_GET['edad']) && isset($_GET['Sexo'])&& isset($_GET['Telefono'])&& isset($_GET['Materia_fa'])&& isset($_GET['horas']))
    {
        $Nombre=$_GET['Nombre'];
        $fecha_Na=$_GET['fecha_Na'];
        $escuela=$_GET['escuela'];
        $edad=$_GET['edad'];
        $Sexo=$_GET['Sexo'];
        $Telefono=$_GET['Telefono'];
        $Materia_fa=$_GET['Materia_fa'];
        $horas=$_GET['horas'];
        $bd = new BaseDeDatos();
        $resultado = $bd->registro($Nombre,$fecha_Na,$escuela,$edad,$Sexo,$Telefono,$Materia_fa,$horas);
        echo '{"usr":'.$resultado.'}';
    }
?>