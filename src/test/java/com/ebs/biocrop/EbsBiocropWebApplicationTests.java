package com.ebs.biocrop;

import com.ebs.biocrop.repository.ProductRepository;
import com.ebs.biocrop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class EbsBiocropWebApplicationTests {

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }
}
