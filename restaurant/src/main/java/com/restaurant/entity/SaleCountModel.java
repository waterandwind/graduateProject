package com.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SaleCountModel {
    Double salePrice;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    LocalDateTime date;
}
