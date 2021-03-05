package org.neethaudupi.hrms.repository;

import org.neethaudupi.hrms.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
