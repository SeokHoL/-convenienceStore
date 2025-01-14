package com.saehimit.shproject.entity.InboundManagement;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 구매발주등록 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 발주 ID

    @Column(nullable = false)
    private String productType; // 품목 타입

    @Column(nullable = false)
    private int quantity; // 수량

    @Column(nullable = false)
    private String requester; // 요청자

    @Column(nullable = false)
    private String status; // 상태 (예: "pending", "completed")

    @Column(nullable = false)
    private LocalDateTime orderDate; // 요청 날짜
}
