package com.geektrade.geektradebackend.service;

import com.geektrade.geektradebackend.dto.Listing;
import com.geektrade.geektradebackend.dto.SearchDto;
import com.geektrade.geektradebackend.entity.ListingEntity;
import com.geektrade.geektradebackend.repository.ListingRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class ListingService {

    private final ListingRepository listingRepository;
    private final ListingImageService listingImageService;

    public Page<Listing> search(Pageable pageable, SearchDto searchDto) {
        Page<ListingEntity> all = listingRepository.findAll(pageable, searchDto.getTerm(), searchDto.getCategoryId(), searchDto.getQuality(), searchDto.getPriceMin(), searchDto.getPriceMax());

        return all.map(entity -> {
            Long primaryImageId = listingImageService.findPrimaryImageIdWithListingId(entity.getId());

            Listing listing = entity.toPojo();
            listing.setPrimaryImageId(primaryImageId);
            return listing;
        });
    }
}
