echo off
set anio=%date:~6,4%
set mes=%date:~3,2%
set dia=%date:~0,2%
set hora=%time:~0,2%
set hora=%hora: =0%
set minuto=%time:~3,2%
set segundo=%time:~6,2% 

mysqldump -hlocalhost -uroot deportes > C:\Users\Pablo\Desktop\Grado\Cursillos\Spring\proyecto\demo\src\main\resources\backups\copia_seguridad_%anio%%mes%%dia%_%hora%%minuto%_.sql
echo exito
exit