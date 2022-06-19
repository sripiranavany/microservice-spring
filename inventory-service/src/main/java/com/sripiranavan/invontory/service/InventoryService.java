package com.sripiranavan.invontory.service;

import com.sripiranavan.invontory.dto.InventoryResponse;
import com.sripiranavan.invontory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode) throws InterruptedException {
        log.info("Checking inventory for skuCode: {}", skuCode);
//        Thread.sleep(10000);
//        need to check the required quantity of each skuCode is in stock
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder().skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0).build())
                .collect(Collectors.toList());

    }
}
