package app.todolist.dto;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import app.todolist.model.Priority;
import app.todolist.model.Task;

public class TaskDTO {
	
	private Long id;
	@NotNull @Size(min=4, max=50)
	private String name;
	@JsonFormat(pattern="dd-MM-yyyy")
	@Future 
	private Date dueDate;
	private Priority priority;
	private boolean completed;
	private boolean overdue;
	
	public TaskDTO() {}
	
	public TaskDTO(Long id, String name, Date dueDate, Priority priority, boolean completed) {
		this.id = id;
		this.name = name;
		this.dueDate = dueDate;
		this.priority = priority;
		this.completed = completed;
		this.overdue = (new Date().after(dueDate)) ? true : false;
	}
	
	public static TaskDTO mapFromEntity(Task t){
		return new TaskDTO(t.getId(), t.getName(), t.getDueDate(), t.getPriority(), t.isAccomplished());
	}
	
	//consider refactoring with Java 8
	public static List<TaskDTO> mapFromEntityList(List<Task> l){
		List<TaskDTO> dtos = new LinkedList<>();
		for(Task t: l){
			dtos.add(mapFromEntity(t));
		}
		return dtos;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
		this.overdue = (new Date().after(dueDate)) ? true : false;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public boolean isOverdue() {
		return overdue;
	}
	
}
