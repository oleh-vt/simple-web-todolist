package app.todolist.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.todolist.model.Priority;
import app.todolist.model.Task;
import app.todolist.repository.ITaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private ITaskRepository tr;
	
	@Transactional(readOnly = true)
	public List<Task> findTasks(){
		List<Task> tasks = tr.findAll();
		return tasks;
	}
	
	@Transactional
	public Task saveTask(String name, Date dueDate, String priority){
		return tr.add(new Task(name, dueDate, Priority.toEnum(priority)));
	}
	
	@Transactional
	public boolean markTaskAsAccomplished(Long id){
		return tr.markAccomplished(id);
	}
	
	@Transactional
	public boolean deleteTask(Long id){
		return tr.remove(id);
	}
	
}
