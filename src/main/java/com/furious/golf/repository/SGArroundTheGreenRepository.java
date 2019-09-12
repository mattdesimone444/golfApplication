package com.furious.golf.repository;

import com.furious.golf.domain.SGArroundTheGreen;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SGArroundTheGreen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SGArroundTheGreenRepository extends JpaRepository<SGArroundTheGreen, Long> {

}
