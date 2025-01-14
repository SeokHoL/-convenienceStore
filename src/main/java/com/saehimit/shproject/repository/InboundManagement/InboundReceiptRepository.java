package com.saehimit.shproject.repository.InboundManagement;

import com.saehimit.shproject.entity.InboundManagement.InboundReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 구매입고 리포지토리
 */
public interface InboundReceiptRepository extends JpaRepository<InboundReceipt, Long> {}