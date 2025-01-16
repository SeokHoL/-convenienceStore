package com.saehimit.shproject.entity.InboundManagement;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * 구매입고 엔티티
 */
public class InboundReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 입고 ID

    @Column(nullable = false)
    private String productType; // 품목 타입

    @Column(nullable = false)
    private Long receivedQuantity; // 입고 수량

    @Column(nullable = false)
    private LocalDateTime receivedDate; // 입고 날짜

    @Column(nullable = false)
    private String warehouseLocation; // 창고 위치
}