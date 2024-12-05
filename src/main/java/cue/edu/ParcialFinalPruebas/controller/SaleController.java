package cue.edu.ParcialFinalPruebas.controller;

import cue.edu.ParcialFinalPruebas.model.Sale;
import cue.edu.ParcialFinalPruebas.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/{productId}")
    public Sale registerSale(@PathVariable Long productId, @RequestParam int quantitySold) {
        return saleService.registerSale(productId, quantitySold);
    }

    @GetMapping("/{productId}")
    public List<Sale> getSalesByProductId(@PathVariable Long productId) {
        return saleService.getSalesByProductId(productId);
    }
}


