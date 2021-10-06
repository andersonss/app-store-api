package br.com.rogalabs.appstoreapi.repositories;

import br.com.rogalabs.appstoreapi.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Anderson on 05/10/2021
 * @project app-store-api
 */
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
