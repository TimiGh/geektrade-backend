package com.geektrade.geektradebackend.repository;

import com.geektrade.geektradebackend.entity.ListingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<ListingEntity, Long> {

    @Query("select l from ListingEntity l where " +
            "(:term IS NULL OR l.title LIKE %:term%)" +
            "AND (:categoryId IS NULL OR l.categoryId = :categoryId)" +
            "AND (:quality IS NULL OR l.quality = :quality)" +
            "AND (:priceMin IS NULL OR l.price >= :priceMin)" +
            "AND (:priceMax IS NULL OR l.price <= :priceMax)")
    Page<ListingEntity> findAll(Pageable pageable, String term, String categoryId, String quality, Long priceMin, Long priceMax);


    Page<ListingEntity> findAllByUser_Id(Long userId, Pageable pageable);
}
