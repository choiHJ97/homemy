package com.acpla.homemy.service;

import com.acpla.homemy.model.Customer;
import com.acpla.homemy.model.Role;
import com.acpla.homemy.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer save(Customer customer){
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customer.setEnabled(true);
        Role role = new Role();
        role.setR_id(1l);
        customer.getRoles().add(role);
        return customerRepository.save(customer);

    }
}
