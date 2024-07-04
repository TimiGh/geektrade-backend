package com.geektrade.geektradebackend.service;

import com.geektrade.geektradebackend.dto.Listing;
import com.geektrade.geektradebackend.dto.SearchDto;
import com.geektrade.geektradebackend.entity.ListingEntity;
import com.geektrade.geektradebackend.entity.ListingImageEntity;
import com.geektrade.geektradebackend.entity.UserEntity;
import com.geektrade.geektradebackend.exception.ListingImageNotFoundException;
import com.geektrade.geektradebackend.exception.ListingNotFoundException;
import com.geektrade.geektradebackend.repository.ListingImageRepository;
import com.geektrade.geektradebackend.repository.ListingRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class ListingService {

    private final ListingRepository listingRepository;
    private final ListingImageService listingImageService;
    private final UserServiceImpl userService;
    private final ListingImageRepository listingImageRepository;

    public Page<Listing> search(Pageable pageable, SearchDto searchDto) {
        Page<ListingEntity> all = listingRepository.findAll(pageable, searchDto.getTerm(), searchDto.getCategoryId(), searchDto.getQuality(), searchDto.getPriceMin(), searchDto.getPriceMax());

        return all.map(entity -> {
//            Long primaryImageId = listingImageService.findPrimaryImageIdWithListingId(entity.getId());

            Listing listing = entity.toPojo();
//            if (primaryImageId != null) {
//                listing.setPrimaryImageId(primaryImageId);
//            }
            return listing;
        });
    }

    public Page<Listing> getPersonalList(Pageable pageable) {
        Page<ListingEntity> all = listingRepository.findAllByUser_Username(userService.getLoggedUserName(), pageable);

        return all.map(ListingEntity::toPojo);
    }

    @Transactional
    public Listing save(Listing listing) {
        UserEntity userEntity = userService.getUserEntity();
        ListingEntity listingEntity = new ListingEntity();

        listingEntity.setUser(userEntity);
        listingEntity.setTitle(listing.getTitle());
        listingEntity.setDescription(listing.getDescription());
        listingEntity.setPrice(listing.getPrice());
        listingEntity.setCategoryId(listing.getCategoryId());
        listingEntity.setQuality(listing.getQuality());
        listingEntity.setIsNegotiable(listing.getIsNegotiable());
        listingEntity.setCreatedAt(LocalDateTime.now());
        listingEntity.setSeenCounter(1);

        return listingRepository.save(listingEntity).toPojo();
    }

    public ListingEntity getListingById(Long id) {
        return listingRepository.findById(id).orElseThrow(() -> new ListingNotFoundException(id.toString()));
    }
}
