package com.innobliss.reservego_router;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveGoWebhookLogRepository extends JpaRepository<ReserveGoWebhookLog, Long> {
}