package com.restaurant.entity.result;

import lombok.Data;

public class ParamLackException extends Exception {
    public ParamLackException(){

    }
    public ParamLackException(String msg){
        super(msg);
    }

}
