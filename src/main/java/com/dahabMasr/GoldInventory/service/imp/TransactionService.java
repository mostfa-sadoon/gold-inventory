package com.dahabMasr.GoldInventory.service.imp;

import com.dahabMasr.GoldInventory.controller.api.TransactionController;
import com.dahabMasr.GoldInventory.exception.InventoryNotFoundException;
import com.dahabMasr.GoldInventory.exception.TransactionNotFoundException;
import com.dahabMasr.GoldInventory.model.Dto.InventoryWithCountRes;
import com.dahabMasr.GoldInventory.model.Dto.PriceRes;
import com.dahabMasr.GoldInventory.model.Dto.TransactionReq;
import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import com.dahabMasr.GoldInventory.model.Entity.TransactionDetail;
import com.dahabMasr.GoldInventory.model.Mapper.Imp.TransactionMapper;
import com.dahabMasr.GoldInventory.repository.TransactionRepository;
import com.dahabMasr.GoldInventory.service.TransactionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements TransactionServiceInterface {

    @Autowired
    TransactionRepository TransactionRepository;
    @Autowired
    InventoryService InventoryService;
    @Autowired
    TransactionDetailIService TransactionDetailIService;
    @Autowired
    TransactionMapper TransactionMapper;

    public Transaction save(Transaction entity){
        return  TransactionRepository.save(entity);
    }

    public Optional<Transaction> find(Long id){
         return TransactionRepository.findById(id);
    }

    public Page<Transaction> findPaginated(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return  TransactionRepository.findAllByOrderByIdDesc(pageable);
    }


    public  TransactionController.Result create(TransactionReq dto){
        TransactionController.Result result = this.calculate(dto.getType(), dto.getAmount());
        TransactionReq trnas =  new TransactionReq(result.amount , dto.getType(), dto.getCustomer());
        Transaction transaction =  this.save(TransactionMapper.toEntity(trnas));
        for (InventoryWithCountRes inv : result.inventories){
            TransactionDetail detail = new TransactionDetail();
            Inventory inventory = new Inventory();
            inventory.setId(inv.getId());
            detail.setInventory(inventory);
            detail.setTransaction(transaction);
            detail.setQuantity(inv.getQuantity());
            detail.setWeight(inv.getQuantity()*inv.getWeight());

            TransactionDetail transdetail =  TransactionDetailIService.save(detail);

            if (transdetail.getId() != null) {
                inventory.setReserved(inv.getReversed() + inv.getQuantity());
                inventory.setType(inv.getType());
                inventory.setWeight(inv.getWeight());
                inventory.setQuantity(inv.getStock_quantity());
                inventory.setName(inv.getName());
                InventoryService.update(inventory);
            }
        }

        result.transaction_id = transaction.getId();
        result.status = transaction.getStatus().toString();
        return  result;
    }

    public  Transaction updateStatus(Long id , String status){
        Transaction trans = TransactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        trans.setStatus(Transaction.Status.valueOf(status));
        TransactionRepository.save(trans);
        List<TransactionDetail> details  = TransactionDetailIService.findAll(trans.getId());
        for (TransactionDetail detail : details) {
            Inventory inv = InventoryService.find(detail.getInventory().getId())
                    .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + detail.getInventory().getId()));
            if(status=="COMPLETED"){
                inv.setQuantity(inv.getQuantity() - detail.getQuantity());
                inv.setReserved(inv.getReserved() - detail.getQuantity());
            }else {
                inv.setReserved(inv.getReserved() - detail.getQuantity());
            }
            InventoryService.save(inv);
        }
        return  trans;
    }

    // this function responsible for algorithm that calculate  quantity of pieces that user will take
    @Override
    public TransactionController.Result calculate(String type, Double amount) {
        TransactionController.Result result = new TransactionController.Result();
        PriceRes price  = new PriceRes();
        List<Inventory> inventories = InventoryService.getInventoriesByTypeOrderDesc(type);
        if (inventories.isEmpty()){
            throw new InventoryNotFoundException(type);
        }
        double remaining = amount;
        List<InventoryWithCountRes> resultList = new ArrayList<>();

        for (Inventory inv : inventories) {
            float priceValue = type.equals("GOLD") ? price.getGoldBaying() : price.getSelverBaying();
            double piecePrice = inv.getWeight() * priceValue;

            int count = 0;
            while (remaining >= piecePrice) {
                if(count>=inv.getQuantity()){
                    break;
                }
                count++;
                remaining -= piecePrice;
            }
            if (count > 0) {
                resultList.add(new InventoryWithCountRes(
                        inv.getId(),
                        inv.getName(),
                        inv.getWeight(),
                        inv.getReserved(),
                        inv.getType(),
                        count,
                        inv.getQuantity()
                ));
            }
        }

        result.remaining = remaining;
        result.setInventories(resultList);
        result.amount = amount - remaining;
        return result;
    }
}
