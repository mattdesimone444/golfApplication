package com.furious.golf.repository;

import com.furious.golf.domain.SGOffTheTee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SGOffTheTee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SGOffTheTeeRepository extends JpaRepository<SGOffTheTee, Long> {

}
