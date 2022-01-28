package com.react_spring_boot.Venues;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardroomRepository extends JpaRepository<Boardroom, Integer> {
}
