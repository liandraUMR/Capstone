package environmentalDataLogging.configurations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/**
 * The type Global controller exception handler.
 */
@ControllerAdvice
class GlobalControllerExceptionHandler
{
    /**
     * Handle exception error message.
     *
     * @param ex the ex
     * @return the error message
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorMessage handleException(MethodArgumentNotValidException ex)
    {
        Stream<String> fieldErrorsStream = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ", " + error.getDefaultMessage());

        Stream<String> globalErrorsStream = ex.getBindingResult().getGlobalErrors().stream()
                .map(error -> error.getObjectName() + ", " + error.getDefaultMessage());

        List<String> errors = concat(fieldErrorsStream, globalErrorsStream).collect(Collectors.toList());

        return new ErrorMessage(errors);
    }

    private static class ErrorMessage
    {
        private final List<String> errors;

        /**
         * Instantiates a new Error message.
         *
         * @param errors the errors
         */
        public ErrorMessage(List<String> errors)
        {
            this.errors = errors;
        }

        /**
         * Gets errors.
         *
         * @return the errors
         */
        public List<String> getErrors()
        {
            return errors;
        }
    }
}