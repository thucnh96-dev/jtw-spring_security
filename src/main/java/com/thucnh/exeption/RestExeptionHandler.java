package com.thucnh.exeption;

import com.thucnh.payload.request.MessageResponse;
import com.thucnh.payload.response.ParamError;
import com.thucnh.response.APIResponse;
import com.thucnh.response.APIStatus;
import com.thucnh.response.ResponseUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice(annotations = RestController.class)
public class RestExeptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = getLogger(RestExeptionHandler.class);



    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    @ResponseBody
    public APIResponse handleGenericException(Exception ex, WebRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("handling exception...");
        }
       return new APIResponse(APIStatus.ERR_BAD_REQUEST,ex.getMessage(),null);
    }

    @ExceptionHandler(value = {InvalidRequestException.class})
    public ResponseEntity<ParamError> handleInvalidRequestException(InvalidRequestException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling InvalidRequestException...");
        }
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getErrors().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getErrors().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ParamError apiError = new ParamError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

        return new ResponseEntity<ParamError>(apiError, HttpStatus.BAD_REQUEST);
    }
}
