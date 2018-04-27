package org.softuni.nuggets.areas.admin.services;

import org.softuni.nuggets.areas.admin.repositories.TeamRepository;
import org.softuni.nuggets.entities.Team;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team getTeam(String team) {
        return this.teamRepository.findByTeamName(team);
    }

    @Override
    public boolean create(Team team) {
        this.teamRepository.save(team);
        return true;
    }

    @Override
    public int getAllTeams() {
        return (int) this.teamRepository.count();
    }
}
