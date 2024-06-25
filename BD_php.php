<?php
    class BaseDeDatos
    {
        private $con; 
        public function __construct()
        {
            $this->con = new PDO('mysql:host=localhost;dbname=autodidatta','root','');
        }
        public function ingreso($User,$password)
        { 
            $sql = $this->con->prepare("SELECT * FROM usuarios WHERE BINARY usuarios.User = '" .$User. "' and BINARY password = '" .$password. "'");
            $sql->execute();
            $respuesta = $sql->fetchall(); 
            if(count($respuesta) > 0) 
            {
                foreach($respuesta as $dato)
                    return $dato['ID'];
            }
            return -1;
        }
        public function registro($Nombre,$fecha_Na,$escuela,$edad,$Sexo,$Telefono,$Materia_fa,$horas)
        {
            //$sql = $this->con->prepare("INSERT INTO estudiante VALUES ('".$Nombre."','".$fecha_Na"','".$escuela."','".$edad."','" .$Sexo."','".Telefono."','".$Materia_fa."','".$horas."')");
            //$sql->execute();
            $sql = $this->con->prepare("INSERT INTO estudiante VALUES ('$Nombre','$fecha_Na','$escuela','$edad','$Sexo','$Telefono','$Materia_fa','$horas')");
            $sql->execute();
            return 1;
        }
        public function eliminar($Nombre)
        {
        	$sql = $this->con->prepare("DELETE FROM ESTUDIANTE WHERE Nombre = '$Nombre'");
        	$sql->execute();
        	return 1;
        }
        public function modificar($Aux,$Nombre,$edad,$escuela,$Telefono,$fecha_Na,$horas,$Sexo,$Materia_fa)
        {
            $sql = $this->con->prepare("DELETE FROM ESTUDIANTE WHERE Nombre = '$Aux'");
            $sql->execute();
            $sql = $this->con->prepare("INSERT INTO ESTUDIANTE VALUES ('$Nombre','$edad','$fecha_Na','$Telefono','$escuela','$horas','$Sexo','$Materia_fa')");
            $sql->execute();
            return 1;
        }
        

    }

?>