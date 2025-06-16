package com.billing.extract;

import com.billing.extract.model.BillingInfo;
import com.billing.extract.service.BillingExtractorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BillingExtractorServiceTests {

    @Autowired
    BillingExtractorService service;

    @Test
    void simpleExtractionWorks() throws Exception {
        String data = "ItemA 10\nItemB 5";
        MockMultipartFile file = new MockMultipartFile("file", "sample.txt", "text/plain", data.getBytes());
        BillingInfo info = service.extract(file, "test");
        assertThat(info.getTotal()).isEqualTo(15);
        assertThat(info.getItems()).hasSize(2);
    }
}
