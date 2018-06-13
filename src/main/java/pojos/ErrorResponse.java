package pojos;

import groovy.transform.builder.Builder;

/**
 * Error response POJO.
 * Note getters, setters, builder and constructors are implemented by Lombok.
 * To install Lombok on Eclipse: https://projectlombok.org/setup/eclipse
 * To install Lombok on IntelliJ: https://projectlombok.org/setup/intellij
 *
 * @author usoboro
 */
@Builder

public class ErrorResponse {
    public String status;
    public String errorCode;
    public String code;
    public String message;
    public String[] errors;
}
