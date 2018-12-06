package com.novik.firstapp.repos;

import com.novik.firstapp.domain.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrRepo extends JpaRepository<Usr, Long> {
    Usr findByUsername(String username);
}