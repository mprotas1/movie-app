package com.protas.movieapp.repository;

import com.protas.movieapp.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByCityAndStreetAndAddressNumber(String city,
                                                          String street,
                                                          Integer addressNumber);
}
