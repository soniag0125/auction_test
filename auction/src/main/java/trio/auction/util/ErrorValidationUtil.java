/**
 * 
 */
package trio.auction.util;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

/**
 * @author lasonia.guama
 *
 */

@RestControllerAdvice
public class ErrorValidationUtil {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidateException(MethodArgumentNotValidException e) {
		Map<String, String> mapa = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(error -> {
			String atributo = ((FieldError) error).getField();
			String mensaje = error.getDefaultMessage();
			mapa.put(atributo, mensaje);
		});
		return mapa;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoSuchElementException.class)
	public String handleValidateException(NoSuchElementException e) {
			return "There is not an auction this moment.";
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidFormatException.class)
	public String handleValidateException(InvalidFormatException e) {
			return "Invalid value, please insert only numbers.";
	}
}