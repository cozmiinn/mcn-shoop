package com.mcn.shoop.services;
import com.mcn.shoop.entities.Address;
import com.mcn.shoop.repositories.AddressRepository;
import com.mcn.shoop.validators.AddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAddress(){
        return addressRepository.findAll();
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address address){
        AddressValidator.validateAddress(address);
        Address currentAddress = addressRepository.findById(id).orElseThrow(RuntimeException::new);
        currentAddress.setStreetLine(address.getStreetLine());
        currentAddress.setPostalCode(address.getPostalCode());
        currentAddress.setCity(address.getCity());
        currentAddress.setCounty(address.getCounty());
        currentAddress.setCountry(address.getCountry());

        return addressRepository.save(currentAddress);
    }

    public void deleteAddress(Long id){
        addressRepository.deleteById(id);
    }

}
