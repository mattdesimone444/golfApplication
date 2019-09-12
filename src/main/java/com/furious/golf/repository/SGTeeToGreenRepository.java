package com.furious.golf.repository;

import com.furious.golf.domain.SGTeeToGreen;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SGTeeToGreen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SGTeeToGreenRepository extends JpaRepository<SGTeeToGreen, Long> {

}
