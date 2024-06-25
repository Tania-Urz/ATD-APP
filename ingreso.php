<?php
    include "BD_php.php";
    if(isset($_GET['User']) && isset($_GET['password']))
    {
        $User=$_GET['User'];
        $password=$_GET['password'];
        $bd = new BaseDeDatos();
        $res = $bd->ingreso($User,$password);
        echo '{"User":'.$res.'}';
    }
?>