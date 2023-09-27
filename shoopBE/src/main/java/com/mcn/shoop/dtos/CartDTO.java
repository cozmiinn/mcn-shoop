package com.mcn.shoop.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDTO {
    private Long id;
    private List<UserDTO> user;
    private List<VoucherDTO> vouchers;
    private List<CartEntryDTO> cartEntries;
    private double totalPrice;
}
