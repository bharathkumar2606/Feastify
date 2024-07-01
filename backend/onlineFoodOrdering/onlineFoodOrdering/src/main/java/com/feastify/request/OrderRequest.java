package com.feastify.request;

import com.feastify.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address deliverAddress;

}
