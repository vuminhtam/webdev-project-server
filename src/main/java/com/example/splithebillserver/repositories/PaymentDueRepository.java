package com.example.splithebillserver.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.splithebillserver.models.PaymentDue;

public interface PaymentDueRepository extends CrudRepository<PaymentDue, Integer> {

}
