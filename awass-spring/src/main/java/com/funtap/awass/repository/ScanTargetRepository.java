package com.funtap.awass.repository;

import com.funtap.awass.jpaentity.ScanTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScanTargetRepository extends JpaRepository<ScanTarget, Long> {
    Page<ScanTarget> findByUsername(String username, Pageable pageable);
}
