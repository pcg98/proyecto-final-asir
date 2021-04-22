echo off
set FECHA= %date%
set FECHA=%FECHA:/=%
mysqldump -hlocalhost -uroot -p deportes > C:\Users\Pablo\Desktop\Grado\2º\proyecto\backupDeportes”%FECHA%”.sql