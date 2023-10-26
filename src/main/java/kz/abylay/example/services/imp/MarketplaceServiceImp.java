package kz.abylay.example.services.imp;

import kz.abylay.example.model.Marketplace;
import kz.abylay.example.repository.MarketplaceRepository;
import kz.abylay.example.services.MarketplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketplaceServiceImp implements MarketplaceService {

    @Autowired
    public MarketplaceRepository marketplaceRepository;
    @Override
    public List<Marketplace> getAllMarketplace() {
        return marketplaceRepository.findAll();
    }

    @Override
    public Marketplace getMarketplaceById(Integer id) {
        return marketplaceRepository.findById(id).orElse(null);
    }

    @Override
    public void addMarketplace(Marketplace m) {
        marketplaceRepository.save(m);
    }

    @Override
    public void updateMarketplace(Marketplace m) {
        marketplaceRepository.save(m);
    }

    @Override
    public void deleteMarketplace(Integer id) {
        marketplaceRepository.deleteById(id);
    }

}
