package com.ftp.process.service;

import com.ftp.process.model.FileTransferConfig;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class FtpTransferService {
    private static final Logger logger = LoggerFactory.getLogger(FtpTransferService.class);

    public void processConfig(FileTransferConfig config, String username, String password) {
        String remotePattern = config.getSourcePattern();
        if (remotePattern == null) {
            logger.warn("No source pattern defined for config {}", config.getId());
            return;
        }
        String formatted = remotePattern.replace("{date}", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));

        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(config.getSourceServer());
            ftp.login(username, password);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            if (config.getDirection() == FileTransferConfig.Direction.INCOMING) {
                try (FileOutputStream fos = new FileOutputStream(Paths.get(config.getLocalFolder(), formatted).toFile())) {
                    ftp.retrieveFile(formatted, fos);
                }
            } else {
                try (FileInputStream fis = new FileInputStream(Paths.get(config.getLocalFolder(), formatted).toFile())) {
                    ftp.storeFile(formatted, fis);
                }
            }
        } catch (IOException e) {
            logger.error("FTP error", e);
        } finally {
            try { ftp.disconnect(); } catch (IOException ignore) {}
        }
    }
}
