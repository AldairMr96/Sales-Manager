package com.company.bazar.service;import com.company.bazar.dto.CreateSaleDTO;import com.company.bazar.dto.MaxSaleDTO;import com.company.bazar.dto.SaleDTO;import com.company.bazar.model.Product;import com.company.bazar.model.Sale;import com.company.bazar.model.SaleProduct;import java.time.LocalDate;import java.util.List;public interface ISaleService {    public  void createSale (CreateSaleDTO sale);    public List<Sale> getSales ();    public Sale findSale (Long codSale);    public void deleteSale(Long codSale);    public  void editSale (CreateSaleDTO sale);    public List<String> findProductSale (Long codSale);    public SaleDTO totalSale (LocalDate dateSale);    public MaxSaleDTO saleMajor ();}