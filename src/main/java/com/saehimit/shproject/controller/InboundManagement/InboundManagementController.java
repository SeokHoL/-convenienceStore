package com.saehimit.shproject.controller.InboundManagement;

import com.saehimit.shproject.entity.InboundManagement.InboundReceipt;
import com.saehimit.shproject.entity.InboundManagement.MiscInbound;
import com.saehimit.shproject.entity.InboundManagement.MiscOutbound;
import com.saehimit.shproject.entity.InboundManagement.PurchaseOrder;
import com.saehimit.shproject.service.InboundManagement.InboundManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class InboundManagementController {
    private final InboundManagementService inboundManagementService;

    // 구매발주등록
    @GetMapping("/purchase-order")
    public String showPurchaseOrders(Model model) {
        model.addAttribute("purchaseOrders", inboundManagementService.getAllPurchaseOrders());
        return "purchase_order"; // 구매발주등록 페이지
    }

    @PostMapping("/purchase-order")
    public String addPurchaseOrder(PurchaseOrder order) {
        inboundManagementService.savePurchaseOrder(order);
        return "redirect:/purchase-order";
    }

    // 구매입고
    @GetMapping("/inbound-receipt")
    public String showInboundReceipts(Model model) {
        model.addAttribute("inboundReceipts", inboundManagementService.getAllInboundReceipts());
        return "inbound_receipt"; // 구매입고 페이지
    }

    @PostMapping("/inbound-receipt")
    public String addInboundReceipt(InboundReceipt receipt) {
        inboundManagementService.saveInboundReceipt(receipt);
        return "redirect:/inbound-receipt";
    }

    // 기타입고
    @GetMapping("/misc-inbound")
    public String showMiscInbounds(Model model) {
        model.addAttribute("miscInbounds", inboundManagementService.getAllMiscInbounds());
        return "misc_inbound"; // 기타입고 페이지
    }

    @PostMapping("/misc-inbound")
    public String addMiscInbound(MiscInbound miscInbound) {
        inboundManagementService.saveMiscInbound(miscInbound);
        return "redirect:/misc-inbound";
    }

    // 기타출고
    @GetMapping("/misc-outbound")
    public String showMiscOutbounds(Model model) {
        model.addAttribute("miscOutbounds", inboundManagementService.getAllMiscOutbounds());
        return "misc_outbound"; // 기타출고 페이지
    }

    @PostMapping("/misc-outbound")
    public String addMiscOutbound(MiscOutbound miscOutbound) {
        inboundManagementService.saveMiscOutbound(miscOutbound);
        return "redirect:/misc-outbound";
    }
}
