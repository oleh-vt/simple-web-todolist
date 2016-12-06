package app.todolist.repository;

import java.util.List;

import app.todolist.model.Task;

public interface ITaskRepository {
	public List<Task> findAll();
	public Task findById(Long id);
	public Task upsert(Task t);
	public void remove(Long id);
}
