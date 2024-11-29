package com.example.simulateur.repository;

import com.example.simulateur.entity.Signalement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignalementRepository extends JpaRepository<Signalement, Long> {}
