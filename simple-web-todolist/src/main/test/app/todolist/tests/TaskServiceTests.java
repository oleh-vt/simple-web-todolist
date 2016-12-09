package app.todolist.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import app.todolist.model.Priority;
import app.todolist.model.Task;
import app.todolist.repository.ITaskRepository;
import app.todolist.service.TaskService;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTests {

	@Mock
	ITaskRepository repository;
	@InjectMocks
	private TaskService target;
	
	
	@Test
	public void findAll_test() {
		List<Task> lt = new LinkedList<>();
		Task t = new Task();
		lt.add(t);
		
		Mockito.when(repository.findAll()).thenReturn(lt);
		
		List<Task> taskList = target.findTasks();
		
		Mockito.verify(repository).findAll();
		
		assertNotNull("Returned list is null", taskList);
		assertEquals("Wrong list size", 1, taskList.size());
		assertSame("Returned wrong object", t, taskList.get(0));
	}
	
	@Test
	public void saveTask_test(){
		String paramName = new String("Random task name");
		Date paramDate = new Date();
		String paramPriority = new String("MEDIUM");
		
		Task expected = new Task(paramName, paramDate, Priority.toEnum(paramPriority));
		
		Mockito.when(repository.add(any(Task.class))).thenReturn(expected);
		
		Task actual = target.saveTask(paramName, paramDate, paramPriority);
		
		Mockito.verify(repository).add(any(Task.class));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void markCompleted_test(){
		
		Mockito.when(repository.markAccomplished(anyLong())).thenReturn(true);
		
		boolean actual = target.markTaskAsAccomplished(1L);
		
		Mockito.verify(repository).markAccomplished(anyLong());
		
		assertTrue(actual);
	}
	
	@Test
	public void deleteTask_test(){
		
		Mockito.when(repository.remove(anyLong())).thenReturn(true);
		
		boolean actual = target.deleteTask(1L);
		
		Mockito.verify(repository).remove(anyLong());
		assertTrue(actual);
	}

}
