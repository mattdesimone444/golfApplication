package com.furious.golf.repository;

import com.furious.golf.domain.SGApproach;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SGApproach entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SGApproachRepository extends JpaRepository<SGApproach, Long> {

}
