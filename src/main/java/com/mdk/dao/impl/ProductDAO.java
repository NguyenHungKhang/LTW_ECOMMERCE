package com.mdk.dao.impl;

import com.mdk.connection.DBConnection;
import com.mdk.dao.IProductDAO;
import com.mdk.models.Category;
import com.mdk.models.Product;
import com.mdk.services.ICategoryService;
import com.mdk.services.impl.CategoryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DBConnection implements IProductDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Product> findAllProductProhibited() {
        return null;
    }

    @Override
    public List<Product> findAllProductPermited() {
        return null;
    }

    @Override
    public void insert(Product product) {
        StringBuilder sql = new StringBuilder("insert into product(name, description, price, promotionalPrice, \\n\" +\n" +
                "                \"quantity, sold, isActive, categoryId, storeId, rating, image1, image2, image3)\\n\" +\n" +
                "                \"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        try {
            conn = getConnection();
            ps = conn.prepareStatement(String.valueOf(sql));
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setDouble(4, product.getPromotionalPrice());
            ps.setInt(5, product.getQuantity());
            ps.setInt(6, product.getSold());
            ps.setBoolean(7, product.isActive());
            ps.setInt(8, product.getCategoryId());
            ps.setInt(9, product.getStoreId());
            ps.setInt(10, product.getRating());
            ps.setString(11, product.getImage1());
            ps.setString(12, product.getImage2());
            ps.setString(13, product.getImage3());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        StringBuilder sql = new StringBuilder("update product \n" +
                "set name = ?, description = ?, price = ?, promotionalPrice = ?, \n" +
                "quantity = ?, sold = ?, isActive = ?, categoryId = ?, storeId = ?, \n" +
                "rating = ?, image1 = ?, image2 = ?, image3 = ?, image4 = ?\n" +
                "where id = ?");
        try {
            conn = getConnection();
            ps = conn.prepareStatement(String.valueOf(sql));
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setDouble(4, product.getPromotionalPrice());
            ps.setInt(5, product.getQuantity());
            ps.setInt(6, product.getSold());
            ps.setBoolean(7, product.isActive());
            ps.setInt(8, product.getCategoryId());
            ps.setInt(9, product.getStoreId());
            ps.setInt(10, product.getRating());
            ps.setString(11, product.getImage1());
            ps.setString(12, product.getImage2());
            ps.setString(13, product.getImage3());
            ps.setInt(14, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        StringBuilder sql = new StringBuilder("delete from product where id = ?");
        try {
            conn = super.getConnection();
            ps = conn.prepareStatement(String.valueOf(sql));
            rs = ps.executeQuery();
            conn = getConnection();
            ps = conn.prepareStatement(String.valueOf(sql));
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getTopSeller(int index) {
        StringBuilder sql = new StringBuilder("select * from product\n" +
                "order by sold DESC\n" +
                "limit ?");
        List<Product> products = new ArrayList<>();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(String.valueOf(sql));
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setPromotionalPrice(rs.getDouble("promotionalPrice"));
                product.setQuantity(rs.getInt("quantity"));
                product.setSold(rs.getInt("sold"));
                product.setActive(rs.getBoolean("isActive"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setStoreId(rs.getInt("storeId"));
                product.setRating(rs.getInt("rating"));
                product.setImage1(rs.getString("image1"));
                product.setImage2(rs.getString("image2"));
                product.setImage3(rs.getString("image3"));
                product.setImage4(rs.getString("image4"));
                product.setCreatedAt(rs.getTimestamp("createdAt"));
                product.setUpdatedAt(rs.getTimestamp("updatedAt"));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findAll() {
        StringBuilder sql = new StringBuilder("select * from product");
        List<Product> products = new ArrayList<>();
        ICategoryService categoryService = new CategoryService();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(String.valueOf(sql));
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                Category category = categoryService.findById(rs.getInt("categoryId"));
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setPromotionalPrice(rs.getDouble("promotionalPrice"));
                product.setQuantity(rs.getInt("quantity"));
                product.setSold(rs.getInt("sold"));
                product.setActive(rs.getBoolean("isActive"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setStoreId(rs.getInt("storeId"));
                product.setRating(rs.getInt("rating"));
                product.setImage1(rs.getString("image1"));
                product.setImage2(rs.getString("image2"));
                product.setImage3(rs.getString("image3"));
                product.setImage4(rs.getString("image4"));
                product.setCreatedAt(rs.getTimestamp("createdAt"));
                product.setUpdatedAt(rs.getTimestamp("updatedAt"));
                product.setCategory(category);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        StringBuilder sql = new StringBuilder("select * from product\n" +
                "where categoryId = ?");
        List<Product> products = new ArrayList<>();
        ICategoryService categoryService = new CategoryService();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(String.valueOf(sql));
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                Category category = categoryService.findById(rs.getInt("categoryId"));
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setPromotionalPrice(rs.getDouble("promotionalPrice"));
                product.setQuantity(rs.getInt("quantity"));
                product.setSold(rs.getInt("sold"));
                product.setActive(rs.getBoolean("isActive"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setStoreId(rs.getInt("storeId"));
                product.setRating(rs.getInt("rating"));
                product.setImage1(rs.getString("image1"));
                product.setImage2(rs.getString("image2"));
                product.setImage3(rs.getString("image3"));
                product.setImage4(rs.getString("image4"));
                product.setCreatedAt(rs.getTimestamp("createdAt"));
                product.setUpdatedAt(rs.getTimestamp("updatedAt"));
                product.setCategory(category);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

}

