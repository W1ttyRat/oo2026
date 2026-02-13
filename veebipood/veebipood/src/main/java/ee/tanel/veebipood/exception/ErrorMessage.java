package ee.tanel.veebipood.exception;

import lombok.Data;

import java.util.Date;

@Data // @getter, @setter, @noargsconstructor


public class ErrorMessage {

    private String message;
    private Date timestamp;
    private int status;
}
