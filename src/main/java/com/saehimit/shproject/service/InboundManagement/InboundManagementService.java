package com.saehimit.shproject.service.InboundManagement;

import com.saehimit.shproject.entity.InboundManagement.InboundReceipt;
import com.saehimit.shproject.entity.InboundManagement.MiscInbound;
import com.saehimit.shproject.entity.InboundManagement.MiscOutbound;
import com.saehimit.shproject.entity.InboundManagement.PurchaseOrder;
import com.saehimit.shproject.repository.InboundManagement.InboundReceiptRepository;
import com.saehimit.shproject.repository.InboundManagement.MiscInboundRepository;
import com.saehimit.shproject.repository.InboundManagement.MiscOutboundRepository;
import com.saehimit.shproject.repository.InboundManagement.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InboundManagementService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final InboundReceiptRepository inboundReceiptRepository;
    private final MiscInboundRepository miscInboundRepository;
    private final MiscOutboundRepository miscOutboundRepository;

    // 구매발주등록 관련 메서드
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public void savePurchaseOrder(PurchaseOrder order) {
        purchaseOrderRepository.save(order);
    }

    // 구매입고 관련 메서드
    public List<InboundReceipt> getAllInboundReceipts() {
        return inboundReceiptRepository.findAll();
    }

    public void saveInboundReceipt(InboundReceipt receipt) {
        inboundReceiptRepository.save(receipt);
    }

    // 기타입고 관련 메서드
    public List<MiscInbound> getAllMiscInbounds() {
        return miscInboundRepository.findAll();
    }

    public void saveMiscInbound(MiscInbound miscInbound) {
        miscInboundRepository.save(miscInbound);
    }

    // 기타출고 관련 메서드
    public List<MiscOutbound> getAllMiscOutbounds() {
        return miscOutboundRepository.findAll();
    }

    public void saveMiscOutbound(MiscOutbound miscOutbound) {
        miscOutboundRepository.save(miscOutbound);
    }
}
