package com.company.bazar.service;import com.company.bazar.model.Product;import java.util.List;public interface IProductService {    public  Product createProduct (Product product);    public List<Product> getProducts ();    public Product findProduct (Long codProduct);    public void deleteProduct (Long codProduct);    public  Product editProduct (Product product);    public List<Product> availableStock ();}