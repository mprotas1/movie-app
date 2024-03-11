package com.protas.movieapp.address;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressReadService {
    private final AddressRepository addressRepository;

    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Page<Address> findAll(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    public boolean addressAlreadyExists(Address address) {
        return addressRepository.findByCityAndStreetAndAddressNumber(
                address.getCity(),
                address.getStreet(),
                address.getAddressNumber()
        ).isPresent();
    }
}
