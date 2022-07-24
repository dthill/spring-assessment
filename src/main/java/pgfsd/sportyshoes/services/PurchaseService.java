package pgfsd.sportyshoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pgfsd.sportyshoes.dto.*;
import pgfsd.sportyshoes.entities.Product;
import pgfsd.sportyshoes.entities.Purchase;
import pgfsd.sportyshoes.entities.User;
import pgfsd.sportyshoes.repositories.ProductCategoryRepository;
import pgfsd.sportyshoes.repositories.PurchaseRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Transactional
    public boolean addToCart(ProductId productId, User buyer) {
        Purchase purchase = purchaseRepository.findByBuyerAndPurchasedOnOrderByCreatedOnDesc(buyer, null);
        if (purchase == null) {
            purchase = new Purchase();
            purchase.setBuyer(buyer);
            purchase.setCreatedOn(getNewCreateDate());
        }

        if (purchase.getProducts() == null) {
            purchase.setProducts(new LinkedList<>());
        }
        Product product = new Product();
        product.setId(productId.getId());
        purchase.getProducts().add(product);
        return purchaseRepository.save(purchase) != null;
    }

    @Transactional
    public CartDto getCurrentCart(User user) {
        Purchase purchase = purchaseRepository.findByBuyerAndPurchasedOnOrderByCreatedOnDesc(user, null);
        if (purchase == null) {
            purchase = new Purchase();
            purchase.setBuyer(user);
            purchase.setCreatedOn(getNewCreateDate());
            purchase.setProducts(new LinkedList<>());
            purchase = purchaseRepository.save(purchase);
        }
        double total = 0.0;
        for (Product product : purchase.getProducts()) {
            total += product.getPrice();
        }
        return new CartDto(purchase, total);
    }

    @Transactional
    public boolean buyCart(CompleteCartDto cart, User user) {
        Purchase purchase = purchaseRepository.findByIdAndBuyer(cart.getPurchaseId(), user);
        if (purchase == null) {
            return false;
        }
        purchase.setCreditCardNumber(cart.getCreditCardNumber());
        purchase.setPurchasedOn(new Date());
        purchase = purchaseRepository.save(purchase);
        return purchase != null;
    }

    public PurchaseAdminDto getPurchaseAdmin(PurchaseFilterDto purchaseFilterDto) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        PurchaseAdminDto purchaseAdminDto = new PurchaseAdminDto();

        if(purchaseFilterDto.getDate() != null &&
                purchaseFilterDto.getDate().equals(simpleDateFormat.parse("01/01/0001"))){
            purchaseAdminDto.setPurchases(purchaseRepository.findAllByPurchasedOn(purchaseFilterDto.getDate()));
        } else if(purchaseFilterDto.getCategoryId() != null ) {
            purchaseAdminDto.setPurchases(purchaseRepository.findAllByCategory(purchaseFilterDto.getCategoryId()));
        } else {
            purchaseAdminDto.setPurchases(purchaseRepository.findAllByPurchasedOnIsNotNull());
        }

        purchaseAdminDto.setCategories(productCategoryRepository.findAll());
        List<Date> dates = purchaseRepository.findDistinctPurchasedOn();
        if(dates == null){
            dates = new LinkedList<>();
        }
        List<String> formattedDates = new LinkedList<>();
        for(Date date : dates){
            formattedDates.add(simpleDateFormat.format(date));
        }
        purchaseAdminDto.setFormattedDates(formattedDates);
        return purchaseAdminDto;
    }


    private Date getNewCreateDate(){
        return new Date();
    }
}
