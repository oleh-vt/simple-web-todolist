package app.todolist.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tasks")
@NamedQueries({
	@NamedQuery(name = "find_all", query = "from Task"),
	@NamedQuery(name = "mark_accomplished", query = "update Task set accomplished = true where id = :id")
})
public class Task {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@Column(name="due_date")
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	@Enumerated(EnumType.STRING)
	private Priority priority;
	private boolean accomplished;
	
	public Task() {}

	public Task(String name, Date dueDate, Priority priority) {
		this.name = name;
		this.dueDate = dueDate;
		this.priority = priority;
		this.setAccomplished(false);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Long getId() {
		return id;
	}

	public boolean isAccomplished() {
		return accomplished;
	}

	public void setAccomplished(boolean accomplished) {
		this.accomplished = accomplished;
	}

}
