package com.mdk.services;

import com.mdk.models.Product;
import com.mdk.paging.Pageble;

import java.util.List;

public interface IProductService {
    void insert(Product product);
    void update(Product product);
    void delete(int id);
    Product findOneByName(String name, int storeId);
    List<Product> getTopSeller(int index);
    List<Product> findAll();
    List<Product> findAll(Pageble pageble, int categoryId);
    List<Product> findByCategoryId(int categoryId);
    Product findOneById(int id);
    int count(int categoryId);
    int count(String status);
    List<Product> findAll(Pageble pageble, String status);


}
