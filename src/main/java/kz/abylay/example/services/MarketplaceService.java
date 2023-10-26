package kz.abylay.example.services;

import kz.abylay.example.model.Marketplace;

import java.util.List;

public interface MarketplaceService {
    List<Marketplace> getAllMarketplace();
    Marketplace getMarketplaceById(Integer id);

    void addMarketplace(Marketplace m);

    void updateMarketplace(Marketplace m);

    void deleteMarketplace(Integer id);
}
