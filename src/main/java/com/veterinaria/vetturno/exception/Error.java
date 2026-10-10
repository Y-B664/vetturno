package com.veterinaria.vetturno.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Error {
    private int status;
    private String mensaje;
    private Map<String, String> errores;
    private String timestamp;

    public Error(int status, String mensaje, Map<String, String> errores) {
        this.status = status;
        this.mensaje = mensaje;
        this.errores = errores;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public int getStatus() {
        return status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Map<String, String> getErrores() {
        return errores;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
