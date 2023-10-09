package com.protas.movieapp.service.address;

import com.protas.movieapp.entity.address.Address;
import com.protas.movieapp.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressReadService {
    private final AddressRepository addressRepository;

    public boolean addressAlreadyExists(Address address) {
        return addressRepository.findByCityAndStreetAndAddressNumber(
                address.getCity(),
                address.getStreet(),
                address.getAddressNumber()
                ).isPresent();
    }

}
