package app.todolist.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tasks")
@NamedQueries({
	@NamedQuery(name = "find_all", query = "from Task"),
	@NamedQuery(name = "mark_accomplished", query = "update Task set accomplished = true where id = :id")
})
@NoArgsConstructor
public class Task extends AbstractEntity{
	
	@Getter @Setter
	private String name;
	
	@Column(name="due_date")
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Date dueDate;
	
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private Priority priority;
	
	@Getter @Setter
	private boolean accomplished;
	
	public Task(String name, Date dueDate, Priority priority) {
		this.name = name;
		this.dueDate = dueDate;
		this.priority = priority;
		this.setAccomplished(false);
	}
}
