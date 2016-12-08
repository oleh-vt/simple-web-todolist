package app.todolist.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import app.todolist.model.Task;

@Repository
public class TaskRepository implements ITaskRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Task> findAll() {
		TypedQuery<Task> query = em.createNamedQuery("find_all", Task.class);
		//query.setParameter("accomplished", accomplished);
		return query.getResultList();
	}
	
	@Override
	public Task findById(Long id){
		return em.find(Task.class, id);
	}
	
	@Override
	public Task add(Task t) {
		em.persist(t);
		return t;
	}
	

	@Override
	public boolean markAccomplished(Long id) {
		Query query = em.createNamedQuery("mark_accomplished");
		query.setParameter("id", id);
		return query.executeUpdate() > 0;
	}

	@Override
	public boolean remove(Long id) {
		Task t = em.find(Task.class, id);
		if(t == null)
			return false;
		em.remove(t);
		return true;
	}

}
