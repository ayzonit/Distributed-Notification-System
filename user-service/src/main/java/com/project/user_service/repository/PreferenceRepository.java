package com.project.user_service.repository;

import com.project.user_service.models.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<UserPreference, Long> {

}
