echo off
set FECHA= %date%
set FECHA=%FECHA:/=%
mysqldump –user=root –password= deportes > backupDeportes”%FECHA%”.sql