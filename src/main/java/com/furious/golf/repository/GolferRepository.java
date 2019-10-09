package com.furious.golf.repository;

import com.furious.golf.domain.Golfer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Golfer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GolferRepository extends JpaRepository<Golfer, Long> {
    Golfer findFirstByAgeIsNull();

}
