package proyecto.com.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor implements HandlerInterceptor {
	
	private static final Log LOGGER = LogFactory.getLog(RequestTimeInterceptor.class);
	//Antes de entrar al metodo del controlador
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}
	//Antes de escupir la vista
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long timeStart = (long) request.getAttribute("startTime");
		LOGGER.info("URL to: '" +request.getRequestURL().toString()+"' in'" +(System.currentTimeMillis()- timeStart)+ "'ms" );
	}

}
