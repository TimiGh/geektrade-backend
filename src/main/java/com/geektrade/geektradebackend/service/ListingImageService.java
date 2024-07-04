package com.geektrade.geektradebackend.service;

import com.geektrade.geektradebackend.entity.ListingEntity;
import com.geektrade.geektradebackend.entity.ListingImageEntity;
import com.geektrade.geektradebackend.exception.ListingImageNotFoundException;
import com.geektrade.geektradebackend.exception.UserNotFoundForIdException;
import com.geektrade.geektradebackend.repository.ListingImageRepository;
import com.geektrade.geektradebackend.repository.ListingRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Component
@Data
public class ListingImageService {

    private final ListingImageRepository listingImageRepository;
    private final ListingRepository listingRepository;

    public Long findPrimaryImageIdWithListingId(Long id) {
        ListingImageEntity entity = listingImageRepository.findByListingIdAndIsPrimaryTrue(id);
        return entity.getId();
    }

    @Transactional
    public Long savePrimary(MultipartFile file, Long listingId, Boolean isPrimary) throws IOException {
//        ListingImageEntity entity = listingImageRepository.findByListingId(listingId);
        ListingEntity listing = listingRepository.findById(listingId).orElseThrow(() -> new UserNotFoundForIdException(listingId));

//        if (entity == null) {
           ListingImageEntity entity = new ListingImageEntity();
//        }

        entity.setIsPrimary(isPrimary);
        entity.setListingId(listingId);
        entity.setContent(Base64.getMimeEncoder().encodeToString(file.getBytes()));
        var ceva = listingImageRepository.save(entity);

        if (isPrimary) {
            listing.setPrimaryImageId(ceva.getId());
        }

        listingRepository.save(listing);

        return ceva.getId();
    }

    public String getListingImageById(Long imageId) {
        ListingImageEntity image = listingImageRepository.findById(imageId).orElseThrow(() -> new ListingImageNotFoundException(imageId.toString()));
        return image.getContent();
    }
}
