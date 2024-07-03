package com.geektrade.geektradebackend.repository;

import com.geektrade.geektradebackend.entity.ListingImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingImageRepository extends JpaRepository<ListingImageEntity, Long> {

    ListingImageEntity findByListingIdAndIsPrimaryTrue(Long listingId);
}
