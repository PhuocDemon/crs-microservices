// path: auth-service/src/main/java/vn/edu/crs/authservice/exception/InvalidCredentialsException.java
// purpose: exception rieng cho loi sai username/password, tranh dung chung voi NoSuchElementException cua JDK

package vn.edu.crs.authservice.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
