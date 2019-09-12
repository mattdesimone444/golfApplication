package com.furious.golf.repository;

import com.furious.golf.domain.Putts;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Putts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PuttsRepository extends JpaRepository<Putts, Long> {

}
