
package com.donence.repository;

import com.donence.model.RecyclePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclePointRepository extends JpaRepository<RecyclePoint, Integer> {
}
