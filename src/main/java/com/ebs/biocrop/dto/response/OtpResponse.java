package com.ebs.biocrop.dto.response;

public class OtpResponse {

    private String phoneNumber;
    private String status;
    private int cooldownSeconds;
    private int expirationMinutes;
    private String message;

    public OtpResponse() {
    }

    public OtpResponse(String phoneNumber, String status, int cooldownSeconds, int expirationMinutes, String message) {
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.cooldownSeconds = cooldownSeconds;
        this.expirationMinutes = expirationMinutes;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCooldownSeconds() {
        return cooldownSeconds;
    }

    public void setCooldownSeconds(int cooldownSeconds) {
        this.cooldownSeconds = cooldownSeconds;
    }

    public int getExpirationMinutes() {
        return expirationMinutes;
    }

    public void setExpirationMinutes(int expirationMinutes) {
        this.expirationMinutes = expirationMinutes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
