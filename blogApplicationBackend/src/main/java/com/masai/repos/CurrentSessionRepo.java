package com.masai.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.CurrentSession;

public interface CurrentSessionRepo extends JpaRepository<CurrentSession, String> {
   CurrentSession findByUuid(String uuid);
}
