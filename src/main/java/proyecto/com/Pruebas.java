package proyecto.com;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import proyecto.com.constant.ViewConstant;

public class Pruebas {
	
	public static void main(String[] args) throws IOException {
		String archivo="copia_seguridad_2021-05-17_09-32-05.sql";
		String ruta_backup=ViewConstant.ARCHIVOS_BACKUPSL+"/"+archivo;
		/*Ejecuccion en windows
		Runtime.getRuntime().exec("cmd /c start "+ViewConstant.PROYECTO+"src/main/resources/scripts/script_restauracion.bat "+ruta_backup);
		*/
		//Linux RESTAURAR
		ProcessBuilder pb = new ProcessBuilder("src/main/resources/backups/script_drive_delete.sh", archivo);
		pb.start();
		System.out.print(ruta_backup);
	}

}
