package com.example.onlineorder;

import com.example.onlineorder.business.concretes.ProductManager;
import com.example.onlineorder.dataAccess.abstracts.ProductDao;
import com.example.onlineorder.entities.concretes.Product;
import com.example.onlineorder.core.utilities.results.DataResult;
//import com.example.onlineorder.core.utilities.results.ErrorResult;
import com.example.onlineorder.core.utilities.results.Result;
//import com.example.onlineorder.core.utilities.results.SuccessDataResult;
//import com.example.onlineorder.core.utilities.results.SuccessResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductManagerTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductManager productManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAll_ShouldReturnListOfProducts() {
        // Given
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Test Product 1", 10.0, (short) 100, "Test Unit", null));
        products.add(new Product(2, "Test Product 2", 20.0, (short) 200, "Test Unit", null));
        when(productDao.findAll()).thenReturn(products);

        // When
        DataResult<List<Product>> result = productManager.getAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.getData().size());
    }

    @Test
    void add_ShouldReturnSuccessResult() {
        // Given
        Product product = new Product(1, "Test Product", 10.0, (short) 100, "Test Unit", null);

        // When
        when(productDao.save(any(Product.class))).thenReturn(product);
        Result result = productManager.add(product);

        // Then
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals("product added", result.getMessage());
    }

    @Test
    void getByProductName_ShouldReturnProduct() {
        // Given
        String productName = "Test Product";
        Product product = new Product(1, productName, 10.0, (short) 100, "Test Unit", null);
        when(productDao.getByProductName(anyString())).thenReturn(product);


        // When
        DataResult<Product> result = productManager.getByProductName(productName);

        // Then
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(productName, result.getData().getProductName());
    }

    
}
