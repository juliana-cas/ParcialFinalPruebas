package cue.edu.ParcialFinalPruebas.service;

import cue.edu.ParcialFinalPruebas.model.Sale;
import cue.edu.ParcialFinalPruebas.model.Product;
import cue.edu.ParcialFinalPruebas.repository.SaleRepository;
import cue.edu.ParcialFinalPruebas.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    public Sale registerSale(Long productId, int quantitySold) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < quantitySold) {
            throw new RuntimeException("Insufficient stock for this product");
        }

        // Reduce stock
        product.setQuantity(product.getQuantity() - quantitySold);
        productRepository.save(product);

        // Register sale
        Sale sale = new Sale(product, quantitySold, LocalDateTime.now());
        return saleRepository.save(sale);
    }

    public List<Sale> getSalesByProductId(Long productId) {
        return saleRepository.findByProductId(productId);
    }
}


