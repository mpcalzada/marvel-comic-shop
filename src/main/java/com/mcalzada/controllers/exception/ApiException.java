package com.mcalzada.controllers.exception;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-15T06:36:53.230Z")

public class ApiException extends Exception
{

    private int code;

    public ApiException(int code, String msg)
    {
        super(msg);
        this.code = code;
    }
}
