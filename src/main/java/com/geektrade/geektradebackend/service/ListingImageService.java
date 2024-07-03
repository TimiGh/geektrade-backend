package com.geektrade.geektradebackend.service;

import com.geektrade.geektradebackend.entity.ListingImageEntity;
import com.geektrade.geektradebackend.repository.ListingImageRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ListingImageService {

    private final ListingImageRepository listingImageRepository;

    public Long findPrimaryImageIdWithListingId(Long id) {
        ListingImageEntity entity = listingImageRepository.findByListingIdAndIsPrimaryTrue(id);
        return entity.getId();
    }
}
