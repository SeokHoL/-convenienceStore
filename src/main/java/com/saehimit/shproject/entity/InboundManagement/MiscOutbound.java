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

//기타출고 엔티티
public class MiscOutbound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기타 출고 ID

    @Column(nullable = false)
    private String description; // 기타 출고 설명

    @Column(nullable = false)
    private Long quantity; // 수량

    @Column(nullable = false)
    private LocalDateTime outboundDate; // 출고 날짜
}
