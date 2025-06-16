package com.vnpt.hethonghotro.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "yeu_cau")
@Data
public class YeuCau {
    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "request_content", columnDefinition = "TEXT")
    private String requestContent;

    @Column(name = "receipt_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime receiptAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_he_thong", nullable = false)
    private HeThong heThong;
}