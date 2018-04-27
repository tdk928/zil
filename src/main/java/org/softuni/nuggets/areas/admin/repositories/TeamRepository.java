package org.softuni.nuggets.areas.admin.repositories;

import org.softuni.nuggets.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByTeamName(String teamName);
}
