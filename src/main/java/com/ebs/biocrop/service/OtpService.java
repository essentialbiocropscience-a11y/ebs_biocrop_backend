package com.ebs.biocrop.service;

import com.ebs.biocrop.dto.request.OtpSendRequest;
import com.ebs.biocrop.dto.response.OtpResponse;

public interface OtpService {

    OtpResponse generateAndSendOtp(OtpSendRequest request);

    void verifyOtp(String phoneNumber, String rawOtp);
}
