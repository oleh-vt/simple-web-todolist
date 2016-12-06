package app.todolist.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import app.todolist.model.Task;

@Repository
public class TaskRepository implements ITaskRepository {

	@PersistenceContext
	private EntityManager em;
	
	
	public List<Task> findAll() {
		TypedQuery<Task> query = em.createNamedQuery("find_all", Task.class);
		return query.getResultList();
	}
	
	public Task findById(Long id){
		return em.find(Task.class, id);
	}
	
	public Task upsert(Task t) {
		return em.merge(t);
	}

	public void remove(Long id) {
		em.remove(em.find(Task.class, id));
	}

}
