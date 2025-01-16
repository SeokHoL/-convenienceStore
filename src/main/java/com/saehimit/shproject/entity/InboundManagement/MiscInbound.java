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
 * 기타입고 엔티티
 */
public class MiscInbound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기타 입고 ID

    @Column(nullable = false)
    private String description; // 기타 입고 설명

    @Column(nullable = false)
    private Long quantity; // 수량

    @Column(nullable = false)
    private LocalDateTime inboundDate; // 입고 날짜
}
