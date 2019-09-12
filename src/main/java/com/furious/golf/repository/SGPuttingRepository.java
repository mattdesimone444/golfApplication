package com.furious.golf.repository;

import com.furious.golf.domain.SGPutting;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SGPutting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SGPuttingRepository extends JpaRepository<SGPutting, Long> {

}
