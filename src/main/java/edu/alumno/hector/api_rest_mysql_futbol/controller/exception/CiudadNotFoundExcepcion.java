package edu.alumno.hector.api_rest_mysql_futbol.controller.exception;


public class CiudadNotFoundExcepcion extends RuntimeException {

    private final String errorCode;

    public CiudadNotFoundExcepcion(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
