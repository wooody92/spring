package com.example.batch.core.repository;

import com.example.batch.core.entity.PayEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<PayEntity, Long> {

    List<PayEntity> findAllByAmountGreaterThanEqual(Long amount);

    Page<PayEntity> findAllByAmountGreaterThanEqual(Long amount, Pageable pageable);
}
