package com.mycompany.proyectoboletas;
/**
 * @author Jorge M.
 */

public class CanastaVaciaException extends Exception {

    public CanastaVaciaException() {
        super();
    }
    
    public CanastaVaciaException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
    
}
