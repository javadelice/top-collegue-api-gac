package dev.diginamic.gac.topcollegue.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import dev.diginamic.gac.topcollegue.exception.ErrorConnectionPicture;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ErrorConnectionPicture.class })
	protected ResponseEntity<Object> handleConflict(ErrorConnectionPicture ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

}
