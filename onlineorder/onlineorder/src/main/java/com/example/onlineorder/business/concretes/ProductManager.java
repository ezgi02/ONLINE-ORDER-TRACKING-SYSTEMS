package com.example.onlineorder.business.concretes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import com.example.onlineorder.business.abstracts.ProductService;
import com.example.onlineorder.core.utilities.results.DataResult;
import com.example.onlineorder.core.utilities.results.ErrorResult;
import com.example.onlineorder.core.utilities.results.Result;
import com.example.onlineorder.core.utilities.results.SuccessDataResult;
import com.example.onlineorder.core.utilities.results.SuccessResult;
import com.example.onlineorder.dataAccess.abstracts.ProductDao;
import com.example.onlineorder.entities.concretes.Product;
//This class instanties the business logic and executes the actual
//product operantions
@Service
public class ProductManager implements ProductService{
	private ProductDao productDao;
	
	@Autowired
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}
	@Override
	public DataResult<List<Product>> getAll() {
		return new SuccessDataResult<List<Product>>
		(this.productDao.findAll(),"Data Listed");
	}
	@Override
	public Result add(Product product) {
		this.productDao.save(product);
		return new SuccessResult("product added");
	}
	
	@Override
	public DataResult<Product> getByProductName(String productName) {
		return new SuccessDataResult<Product>
		(this.productDao.getByProductName(productName),"Data Listed");
	}
	
	@Override
	public DataResult<Product> getByProductNameAndCategory_CategoryId(String productName, int categoryId) {
		///buiness codes
		return new SuccessDataResult<Product>
		(this.productDao.getByProductNameAndCategory_CategoryId(productName,categoryId),"Data Listed");
	}
	
	@Override
	public DataResult<List<Product>> getByProductNameOrCategory_CategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductNameOrCategory_CategoryId(productName, categoryId),"Data Listed");
	}
	
	@Override
	public DataResult<List<Product>> getByCategory_CategoryIdIn(List<Integer> categories) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByCategory_CategoryIdIn(categories),"Data Listed");
	}
	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductNameContains(productName),"Data Listed");	
	}
	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductNameStartsWith(productName),"Data Listed");
	}
	@Override
	public DataResult<List<Product>> getByNameOrCategory(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByNameOrCategory(productName, categoryId),"Data Listed");
	}
	@Override
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<Product>>
		(this.productDao.findAll(pageable).getContent());
	}
	@Override
	public DataResult<List<Product>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.DESC,"unitPrice");
		return new SuccessDataResult<List<Product>>
		(this.productDao.findAll(sort),"Succesfull");
	   
	}
	//Product update is done  here
	@Override
	public Result updateProduct(Product product) {
	    Product existingProduct = productDao.findById(product.getId()).orElse(null);
	    if (existingProduct == null) {
	        return new ErrorResult("Product not found");
	    }
	    
	    //Update operations
	    existingProduct.setProductName(product.getProductName());
	    existingProduct.setUnitPrice(product.getUnitPrice());
	    existingProduct.setUnitsInStock(product.getUnitsInStock());
	    existingProduct.setQuantityPerUnit(product.getQuantityPerUnit());
	    existingProduct.setCategory(product.getCategory());
	    
	   //Save the updated product to the database
	    productDao.save(existingProduct);
	    
	    //Return successful result
	    return new SuccessResult("Product update");
	}
	//Product delete  
	@Override
	public Result deleteProduct(int productId) {
	    Product existingProduct = productDao.findById(productId).orElse(null);
	    if (existingProduct == null) {
	        return new ErrorResult("Product not found");
	    }
	    
	   //Delete the product from the database
	    productDao.delete(existingProduct);
	    
	    //Return successful result
	    return new SuccessResult("Product delete");
	}
	@Override
	public DataResult<List<Product>> getByCategory(int categoryId) {
	    List<Product> products = productDao.getByCategory_CategoryId(categoryId);
	    return new SuccessDataResult<>(products, "Products listed by category");
	}

	@Override
	public DataResult<List<Product>> getByPriceRange(double minPrice, double maxPrice) {
	    List<Product> products = productDao.getByUnitPriceBetween(minPrice, maxPrice);
	    return new SuccessDataResult<>(products, "Products listed by category");
	}

}
