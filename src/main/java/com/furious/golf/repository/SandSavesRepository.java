package com.furious.golf.repository;

import com.furious.golf.domain.SandSaves;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SandSaves entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SandSavesRepository extends JpaRepository<SandSaves, Long> {

}
