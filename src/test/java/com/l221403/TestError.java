package com.l221403;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestError {

    
    public void testError() {
        ResponseError responseError;
        RequestData requestData = new RequestData();
        requestData.number1 = "2";
        requestData.number2 = "a";

        AddUseCaseControl addUseCaseControl = new AddUseCaseControl();
        addUseCaseControl.execute(requestData);
        responseError = addUseCaseControl.getResError();

        assertEquals("ERROR_INPUT", responseError.content);

        
    }

    @Test
    public void testError2(){
        ResponseError responseError;
        RequestData requestData = new RequestData();
        requestData.number1 = "2";
        requestData.number2 = "a";

        UIConsoleOutput output = new UIConsoleOutput();

        InputBoundary  addUseCaseControl = new AddUseCaseControl(output);
        addUseCaseControl.execute(requestData);

        
        //responseError = addUseCaseControl.getResError();

        assertEquals("ERROR_INPUT", output.getResponseError().content);
    }

}
