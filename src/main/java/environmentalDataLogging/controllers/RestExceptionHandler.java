package environmentalDataLogging.controllers;

import environmentalDataLogging.exceptions.ResourceNotFoundException;
import environmentalDataLogging.models.ErrorDetailModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * The type Rest exception handler.
 */
@ControllerAdvice
public class RestExceptionHandler
{
    /**
     * Handle resource not found exception response entity.
     *
     * @param rnfe    the rnfe
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request)
    {
        ErrorDetailModel errorDetail = new ErrorDetailModel();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());
        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }
}
