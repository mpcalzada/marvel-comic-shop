package com.mcalzada.controllers.exception;

import com.google.gson.Gson;
import lombok.Getter;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-15T06:36:53.230Z")

public class ApiException extends Exception
{

    @Getter
    private int code;

    public ApiException(int code, String msg)
    {
        super(msg);
        this.code = code;
    }

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }
}
