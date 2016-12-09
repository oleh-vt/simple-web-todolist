package app.todolist.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import app.todolist.model.Priority;
import app.todolist.model.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
public class TaskDTO {
	
	public static final String DATE_PATTERN="dd-MM-yyyy";
	
	@Getter @Setter
	private Long id;
	
	@NotNull @Size(min=4, max=30)
	@Getter @Setter
	private String name;
	
	@JsonFormat(pattern=DATE_PATTERN, timezone="EET")
	@JsonSerialize(using=CustomDateSerializer.class)
	@Getter @Setter
	private Date dueDate;
	
	@Getter @Setter
	private Priority priority;
	
	@Getter @Setter
	private boolean completed;
	
	@Getter
	private boolean overdue;
	
	
	public TaskDTO(Long id, String name, Date dueDate, Priority priority, boolean completed) {
		this.id = id;
		this.name = name;
		this.dueDate = dueDate;
		this.priority = priority;
		this.completed = completed;
		this.overdue = setOverdue(dueDate);
	}
	
	public static TaskDTO mapFromEntity(Task t){
		return new TaskDTO(t.getId(), t.getName(), t.getDueDate(), t.getPriority(), t.isAccomplished());
	}
	
	public static List<TaskDTO> mapFromEntityList(List<Task> l){
		List<TaskDTO> dtos = new LinkedList<>();
		for(Task t: l){
			dtos.add(mapFromEntity(t));
		}
		return dtos;
	}

	private boolean setOverdue(Date duedate){
		SimpleDateFormat sf = new SimpleDateFormat(DATE_PATTERN);
		Date today = new Date();
		return !(today.before(duedate) || sf.format(today).equals(sf.format(duedate)));
	}
	
}
