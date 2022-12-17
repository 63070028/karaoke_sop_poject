package com.example.paymentservice.repository;



import com.example.paymentservice.pojo.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    @Query(value = "SELECT * FROM PAYMENTS WHERE _ID = ?1", nativeQuery = true)
    Payment findByPaymentId(String _id);
}
