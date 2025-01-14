package com.saehimit.shproject.repository.InboundManagement;

import com.saehimit.shproject.entity.InboundManagement.MiscOutbound;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 기타출고 리포지토리
 */
public interface MiscOutboundRepository extends JpaRepository<MiscOutbound, Long> {}