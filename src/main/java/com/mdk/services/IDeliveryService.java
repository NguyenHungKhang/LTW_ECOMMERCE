package com.mdk.services;

import com.mdk.models.Delivery;

import java.util.List;

public interface IDeliveryService {
    List<Delivery> findAll();
    void insert(Delivery delivery);
    void edit(Delivery delivery);
    void deleteSoft(Delivery delivery);
    void restore(Delivery delivery);
    void updateStatus(Delivery delivery);
}
