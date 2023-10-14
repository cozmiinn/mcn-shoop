package com.mcn.shoop.services;
import com.mcn.shoop.dtos.AddressDTO;
import com.mcn.shoop.entities.Address;
import com.mcn.shoop.mappers.AddressStructMapper;
import com.mcn.shoop.repositories.AddressRepository;
import com.mcn.shoop.validators.AddressValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressStructMapper addressStructMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, AddressStructMapper addressStructMapper){
        this.addressRepository = addressRepository;
        this.addressStructMapper = addressStructMapper;
    }

    public List<AddressDTO> getAddresss(){
        List<Address> getAddresss = addressRepository.findAll();
        List<AddressDTO> addressDTOS;
        addressDTOS = getAddresss
                .stream()
                .map(addressStructMapper::addressToAddressDto)
                .collect(Collectors.toList());
        return addressDTOS;
    }

    public AddressDTO getAddress(Long id){
        Address getAddress = addressRepository.findById(id).orElse(null);
        return addressStructMapper.addressToAddressDto(getAddress);
    }

    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = addressStructMapper.addressDtoToAddress(addressDTO);
        Address create = addressRepository.save(address);
        return addressStructMapper.addressToAddressDto(create);
    }


    public AddressDTO updateAddress(Long id, AddressDTO addressDTO){
        AddressValidator.validateAddress(addressDTO);

        Address currentAddress = addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address with id " + id + " not found!"));

        currentAddress.setStreetLine(addressDTO.getStreetLine());
        currentAddress.setPostalCode(addressDTO.getPostalCode());
        currentAddress.setCity(addressDTO.getCity());
        currentAddress.setCounty(addressDTO.getCounty());
        currentAddress.setCountry(addressDTO.getCountry());

        currentAddress = addressRepository.save(currentAddress);

        return addressStructMapper.addressToAddressDto(currentAddress);
    }

    public void deleteAddress(Long id){
        addressRepository.deleteById(id);
    }
}
