package io.emailer.repository;

import io.emailer.entity.MailProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailProviderRepository extends JpaRepository<MailProvider, Long> {
    List<MailProvider> findAllByOrderByPriority();
}
