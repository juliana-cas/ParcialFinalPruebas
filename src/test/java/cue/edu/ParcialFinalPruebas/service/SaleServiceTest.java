package cue.edu.ParcialFinalPruebas.service;

import cue.edu.ParcialFinalPruebas.model.Product;
import cue.edu.ParcialFinalPruebas.model.Sale;
import cue.edu.ParcialFinalPruebas.repository.ProductRepository;
import cue.edu.ParcialFinalPruebas.repository.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SaleService saleService;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product(1L, "Laptop", "Electronics", 10, 1500.00);
    }

    @Test
    void testRegisterSale() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(saleRepository.save(any(Sale.class))).thenReturn(new Sale(product, 2, LocalDateTime.now()));

        Sale sale = saleService.registerSale(1L, 2);

        assertNotNull(sale);
        assertEquals(8, product.getQuantity());
        verify(productRepository, times(1)).save(product);
        verify(saleRepository, times(1)).save(any(Sale.class));
    }
}

