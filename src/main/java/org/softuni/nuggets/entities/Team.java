package org.softuni.nuggets.entities;

import javax.persistence.*;

import static org.softuni.nuggets.areas.contants.Constans.TEAMS;

@Entity
@Table(name = TEAMS)
public class Team {
    private static final String TEAM_NAME = "team_name";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = TEAM_NAME)
    private String teamName;

    private int residue;

    public Team() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getResidue() {
        return this.residue;
    }

    public void setResidue(int residue) {
        this.residue = residue;
    }
}
