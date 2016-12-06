package app.todolist.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		Task t = null;
		if(task.getId() != null){
			t = tr.findById(task.getId());
			t.setName(task.getName());
			t.setDueDate(task.getDueDate());
			t.setPriority(task.getPriority());
			t.setAccomplished(task.isCompleted());
		}
		else{
			t = tr.upsert(new Task(task.getName(), task.getDueDate(), task.getPriority()));
		}
		return t;
	}
	
	@Transactional
	public void deleteTask(Long id){
		tr.remove(id);
	}
	
}
