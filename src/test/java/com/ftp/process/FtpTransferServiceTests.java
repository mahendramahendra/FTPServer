package com.ftp.process;

import com.ftp.process.model.FileTransferConfig;
import com.ftp.process.repository.FileTransferConfigRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FtpTransferServiceTests {
    @Autowired
    FileTransferConfigRepository repository;

    @Test
    void repositoryLoads() {
        FileTransferConfig cfg = new FileTransferConfig();
        cfg.setSourceServer("localhost");
        repository.save(cfg);
        assertThat(repository.findAll()).isNotEmpty();
    }
}
