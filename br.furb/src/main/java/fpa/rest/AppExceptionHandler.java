package fpa.rest;

import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class AppExceptionHandler implements ExceptionMapper<RuntimeException> {

	@Override
	public Response toResponse(RuntimeException arg0) {
		Throwable ex = arg0; 
		while (ex.getCause() != null && !ex.getClass().equals(arg0.getCause())) {
			ex = ex.getCause();
		}
		if (ex instanceof ConstraintViolationException) {
			Iterator<ConstraintViolation<?>> iterator = ((ConstraintViolationException)ex).getConstraintViolations().iterator();
			StringBuilder sb = new StringBuilder();
			while (iterator.hasNext()) {
				sb.append(iterator.next().getMessage());
				sb.append(" ");
			}
			return Response.status(Status.BAD_REQUEST).entity(new Exception(sb.toString())).build();
		}else{
			return Response.status(Status.BAD_REQUEST).entity(new Exception(ex.toString())).build();
		}
	}
}