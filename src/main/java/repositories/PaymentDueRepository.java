package repositories;

import org.springframework.data.repository.CrudRepository;

import models.PaymentDue;

public interface PaymentDueRepository extends CrudRepository<PaymentDue, Integer> {

}
