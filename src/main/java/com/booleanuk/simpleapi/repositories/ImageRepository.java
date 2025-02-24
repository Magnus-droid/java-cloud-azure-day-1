package com.booleanuk.simpleapi.repositories;

import com.booleanuk.simpleapi.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
