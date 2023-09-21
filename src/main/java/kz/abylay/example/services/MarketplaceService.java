package kz.abylay.example.services;

import kz.abylay.example.model.Marketplace;

import java.util.List;

public interface MarketplaceService {
    List<Marketplace> getAllMarketplace();
    Marketplace getMarketplaceById(Integer id);
}
