package kz.abylay.example.repository;

import kz.abylay.example.model.Marketplace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketplaceRepository extends JpaRepository<Marketplace, Integer> {
}
