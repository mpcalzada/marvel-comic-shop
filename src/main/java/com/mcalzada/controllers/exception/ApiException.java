package com.mcalzada.controllers.exception;

import com.google.gson.Gson;
import lombok.Getter;

/**
 * ApiException is a class used for identifying expected errors and handling responses to API
 *
 * @author Marco Calzada
 * @version 1.0
 */
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
