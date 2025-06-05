package com.ftp.process.service;

import com.ftp.process.model.FileTransferConfig;
import com.ftp.process.repository.FileTransferConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;

@Component
@EnableScheduling
public class TransferScheduler {
    private static final Logger logger = LoggerFactory.getLogger(TransferScheduler.class);
    private final FileTransferConfigRepository repository;
    private final SecretService secretService;
    private final FtpTransferService ftpTransferService;

    @Value("${aws.region:us-east-1}")
    private String awsRegion;

    public TransferScheduler(FileTransferConfigRepository repository, SecretService secretService, FtpTransferService ftpTransferService) {
        this.repository = repository;
        this.secretService = secretService;
        this.ftpTransferService = ftpTransferService;
    }

    @Scheduled(fixedDelayString = "${transfer.poll.delay.ms:60000}")
    public void poll() {
        for (FileTransferConfig cfg : repository.findAll()) {
            try {
                String creds = secretService.getSecret(cfg.getCredentialsSecretName(), Region.of(awsRegion));
                String[] pair = creds.split(":" ,2);
                ftpTransferService.processConfig(cfg, pair[0], pair[1]);
            } catch (Exception e) {
                logger.error("Error processing config {}", cfg.getId(), e);
            }
        }
    }
}
