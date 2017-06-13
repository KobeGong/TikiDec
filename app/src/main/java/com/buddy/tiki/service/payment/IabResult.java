package com.buddy.tiki.service.payment;

public class IabResult {
    private int f1089a;
    private String f1090b;

    public IabResult(int response, String message) {
        this.f1089a = response;
        if (message == null || message.trim().length() == 0) {
            this.f1090b = IabHelper.getResponseDesc(response);
        } else {
            this.f1090b = message + " (response: " + IabHelper.getResponseDesc(response) + ")";
        }
    }

    public int getResponse() {
        return this.f1089a;
    }

    public String getMessage() {
        return this.f1090b;
    }

    public boolean isSuccess() {
        return this.f1089a == 0;
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public String toString() {
        return "IabResult: " + getMessage();
    }
}
