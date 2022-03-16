package com.mcalzada.controllers.exception;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-15T06:36:53.230Z")

public class NotFoundException extends ApiException
{

    private int code;

    public NotFoundException(int code, String msg)
    {
        super(code, msg);
        this.code = code;
    }
}
