package com.ftp.process.repository;

import com.ftp.process.model.FileTransferConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileTransferConfigRepository extends JpaRepository<FileTransferConfig, Long> {
}
