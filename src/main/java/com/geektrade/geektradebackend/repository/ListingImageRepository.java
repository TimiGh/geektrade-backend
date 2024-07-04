package com.geektrade.geektradebackend.repository;

import com.geektrade.geektradebackend.entity.ListingImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListingImageRepository extends JpaRepository<ListingImageEntity, Long> {

    ListingImageEntity findByListingIdAndIsPrimaryTrue(Long listingId);

    ListingImageEntity findByListingId(Long listingId);
}
