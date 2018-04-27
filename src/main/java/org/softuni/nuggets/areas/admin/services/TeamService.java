package org.softuni.nuggets.areas.admin.services;

import org.softuni.nuggets.entities.Team;

public interface TeamService {
    Team getTeam(String team);

    boolean create(Team team);

    int getAllTeams();
}
