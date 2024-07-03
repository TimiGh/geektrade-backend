package com.geektrade.geektradebackend.controller;

import com.geektrade.geektradebackend.dto.Listing;
import com.geektrade.geektradebackend.dto.SearchDto;
import com.geektrade.geektradebackend.service.ListingService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/listings")
@Data
public class ListingController {

    private final ListingService listingService;

    @PostMapping(path = "/search")
    public Page<Listing> searchListing(Pageable pageable,
                                       @RequestParam(required = false) String term,
                                       @RequestParam(required = false) String categoryId,
                                       @RequestParam(required = false) Long priceMin,
                                       @RequestParam(required = false) Long priceMax,
                                       @RequestParam(required = false) String quality
    ) {
        return listingService.search(pageable, new SearchDto(term,categoryId,priceMin,priceMax,quality));
    }
}
