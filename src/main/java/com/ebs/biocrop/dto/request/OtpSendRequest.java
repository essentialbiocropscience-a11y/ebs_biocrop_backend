package com.ebs.biocrop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class OtpSendRequest {

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a valid 10-digit number")
    private String phoneNumber;

    public OtpSendRequest() {
    }

    public OtpSendRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber != null ? phoneNumber.trim() : null;
    }
}
