
package com.mdk.dao;

import com.mdk.models.Product;
import com.mdk.models.Store;
import com.mdk.models.Store_1000;
import com.mdk.models.User;

import java.util.List;

public interface IStoreDAO {
    List<Store> findAll();
    List<Store_1000> find1000StoresLatestCreationTime();
    int totalStores();
    List<Store> topStores();
}
