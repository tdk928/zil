package org.softuni.nuggets.entities;


import javax.persistence.*;
import java.util.Date;

import static org.softuni.nuggets.areas.contants.Constans.EVENT_TABLE;

@Entity
@Table(name = EVENT_TABLE)
public class Event {
	private static final String START = "start";
	private static final String END = "end";
	@Id
	@GeneratedValue(strategy= GenerationType.TABLE)
	private Long id;

	private String title;
	private String description;

	@Column(name= START)
	private Date start;

	@Column(name= END)
	private Date end;
//
//	@ManyToMany
//	private List<Employee> employees;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Event(String title, String description, Date start, Date end) {
		super();
		this.title = title;
		this.description = description;
		this.start = start;
		this.end = end;

	}
	public Event() {
		super();
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", description="
				+ description + ", start=" + start + ", end=" + end + "]";
	}
}
