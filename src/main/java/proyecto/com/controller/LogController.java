package proyecto.com.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import proyecto.com.service.LogService;

public class LogController {

	private static final Log Logger = LogFactory.getLog(LogController.class);
	@Autowired
	@Qualifier("logService")
	private LogService logService;
	
	//Listar todos los logs
}
