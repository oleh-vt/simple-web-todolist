package app.todolist.repository;

import java.util.List;

import app.todolist.model.Task;

public interface ITaskRepository {
	public List<Task> findAll();
	public Task findById(Long id);
	public Task add(Task t);
	public boolean markAccomplished(Long id);
	public boolean remove(Long id);
}
