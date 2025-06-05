package com.ftp.process.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class FileTransferConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum Direction { INCOMING, OUTGOING }

    @Enumerated(EnumType.STRING)
    private Direction direction;

    private String sourceServer;
    private String credentialsSecretName;
    private String sourcePattern;
    private Long minSize;
    private Long maxSize;
    private Integer dayOnDayRowDiff;
    private String targetPattern;
    private String sourceContactEmail;
    private String sourceContactName;
    private String sourceContactPhone;
    private String targetContactName;
    private String targetContactEmail;
    private String targetContactPhone;
    private LocalTime expectedReceiveTime;
    @Column(length = 1000)
    private String purpose;
    @Column(length = 1000)
    private String impactIfNotLoaded;
    private Integer escalationDelayMinutes;

    private String localFolder;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }

    public String getSourceServer() { return sourceServer; }
    public void setSourceServer(String sourceServer) { this.sourceServer = sourceServer; }

    public String getCredentialsSecretName() { return credentialsSecretName; }
    public void setCredentialsSecretName(String credentialsSecretName) { this.credentialsSecretName = credentialsSecretName; }

    public String getSourcePattern() { return sourcePattern; }
    public void setSourcePattern(String sourcePattern) { this.sourcePattern = sourcePattern; }

    public Long getMinSize() { return minSize; }
    public void setMinSize(Long minSize) { this.minSize = minSize; }

    public Long getMaxSize() { return maxSize; }
    public void setMaxSize(Long maxSize) { this.maxSize = maxSize; }

    public Integer getDayOnDayRowDiff() { return dayOnDayRowDiff; }
    public void setDayOnDayRowDiff(Integer dayOnDayRowDiff) { this.dayOnDayRowDiff = dayOnDayRowDiff; }

    public String getTargetPattern() { return targetPattern; }
    public void setTargetPattern(String targetPattern) { this.targetPattern = targetPattern; }

    public String getSourceContactEmail() { return sourceContactEmail; }
    public void setSourceContactEmail(String sourceContactEmail) { this.sourceContactEmail = sourceContactEmail; }

    public String getSourceContactName() { return sourceContactName; }
    public void setSourceContactName(String sourceContactName) { this.sourceContactName = sourceContactName; }

    public String getSourceContactPhone() { return sourceContactPhone; }
    public void setSourceContactPhone(String sourceContactPhone) { this.sourceContactPhone = sourceContactPhone; }

    public String getTargetContactName() { return targetContactName; }
    public void setTargetContactName(String targetContactName) { this.targetContactName = targetContactName; }

    public String getTargetContactEmail() { return targetContactEmail; }
    public void setTargetContactEmail(String targetContactEmail) { this.targetContactEmail = targetContactEmail; }

    public String getTargetContactPhone() { return targetContactPhone; }
    public void setTargetContactPhone(String targetContactPhone) { this.targetContactPhone = targetContactPhone; }

    public LocalTime getExpectedReceiveTime() { return expectedReceiveTime; }
    public void setExpectedReceiveTime(LocalTime expectedReceiveTime) { this.expectedReceiveTime = expectedReceiveTime; }

    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }

    public String getImpactIfNotLoaded() { return impactIfNotLoaded; }
    public void setImpactIfNotLoaded(String impactIfNotLoaded) { this.impactIfNotLoaded = impactIfNotLoaded; }

    public Integer getEscalationDelayMinutes() { return escalationDelayMinutes; }
    public void setEscalationDelayMinutes(Integer escalationDelayMinutes) { this.escalationDelayMinutes = escalationDelayMinutes; }

    public String getLocalFolder() { return localFolder; }
    public void setLocalFolder(String localFolder) { this.localFolder = localFolder; }
}
