<?php
    include "BD_php.php";
    if(isset($_GET['Aux']) && isset($_GET['Nombre']) && isset($_GET['fecha_Na']) && isset($_GET['escuela']) && isset($_GET['edad'])&& isset($_GET['Sexo'])&& isset($_GET['Telefono'])&& isset($_GET['Materia_fa'])&& isset($_GET['horas']))
        {
            $Aux=$_GET['Aux'];
            $Nombre=$_GET['Nombre'];
            $edad=$_GET['fecha_Na'];
            $fecha_na=$_GET['escuela'];
            $Materia_fa=$_GET['edad'];
            $Telefono=$_GET['Sexo'];
            $escuela=$_GET['Telefono'];
            $horas=$_GET['Materia_fa'];
            $Sexo=$_GET['horas'];
            $bd = new BaseDeDatos();
            $resultado = $bd->modificar($fecha_Na,$Nombre,$edad,$Materia_fa,$Telefono,$horas,$Sexo,$escuela);
            echo '{"usr":'.$resultado.'}';
        }
?>