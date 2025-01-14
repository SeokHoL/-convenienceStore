package com.saehimit.shproject.repository.InboundManagement;

import com.saehimit.shproject.entity.InboundManagement.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 구매발주등록 리포지토리
 */
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {}