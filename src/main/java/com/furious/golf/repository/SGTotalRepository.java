package com.furious.golf.repository;

import com.furious.golf.domain.SGTotal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SGTotal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SGTotalRepository extends JpaRepository<SGTotal, Long> {

}
