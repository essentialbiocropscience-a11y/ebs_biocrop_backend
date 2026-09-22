package com.ebs.biocrop;

import com.ebs.biocrop.dto.response.OtpResponse;
import com.ebs.biocrop.repository.ProductRepository;
import com.ebs.biocrop.repository.UserRepository;
import com.ebs.biocrop.service.AuthService;
import com.ebs.biocrop.service.OtpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private ProductRepository productRepository;

    @MockitoBean
    private OtpService otpService;

    @MockitoBean
    private AuthService authService;

    @Test
    void healthCheckShouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/api/v1/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.status").value("UP"))
                .andExpect(jsonPath("$.data.database").value("MongoDB Atlas (seller_hub)"));
    }

    @Test
    void sendOtpShouldSucceedForValidPhoneNumber() throws Exception {
        when(otpService.generateAndSendOtp(any())).thenReturn(
                new OtpResponse("98******10", "OTP_SENT", 60, 5, "OTP dispatched successfully")
        );

        Map<String, String> request = Map.of("phoneNumber", "9876543210");

        mockMvc.perform(post("/api/v1/auth/otp/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.status").value("OTP_SENT"));
    }

    @Test
    void profileEndpointWithoutJwtShouldBeUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/user/profile"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Unauthorized"));
    }
}
