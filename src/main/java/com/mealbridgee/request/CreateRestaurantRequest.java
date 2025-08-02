package com.mealbridgee.request;

import com.mealbridgee.model.Address;
import com.mealbridgee.model.ContactInformation;
import com.mealbridgee.service.RestaurantService;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String>images;


}
