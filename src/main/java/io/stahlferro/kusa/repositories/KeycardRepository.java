package io.stahlferro.kusa.repositories;

import io.stahlferro.kusa.models.Keycard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KeycardRepository extends JpaRepository<Keycard, UUID>{

}
