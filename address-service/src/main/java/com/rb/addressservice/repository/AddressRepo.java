package com.rb.addressservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rb.addressservice.entities.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

	Address findByEmpid(long empid);

}
