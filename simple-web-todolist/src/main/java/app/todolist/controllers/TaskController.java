package app.todolist.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import app.todolist.dto.TaskDTO;
import app.todolist.dto.ValidationDTO;
import app.todolist.model.Task;
import app.todolist.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService ts;
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<TaskDTO> findTasks(){
		List<Task> tasks = ts.findTasks();
		return TaskDTO.mapFromEntityList(tasks);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST)
	public TaskDTO addTask(	@RequestParam(value = "name", required = true) String name,
							@RequestParam(value = "dueDate", required = true) 
											@DateTimeFormat(pattern = TaskDTO.DATE_PATTERN)Date dueDate,
							@RequestParam(value = "priority", required = true) String priority){
		
		return TaskDTO.mapFromEntity(ts.saveTask(name.trim(), dueDate, priority.trim()));
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> markAccomplished(@RequestParam (value="id", required=true) Long id){
		boolean success = ts.markTaskAsAccomplished(id);
		return (success) ?  new ResponseEntity<>(HttpStatus.NO_CONTENT) : 
							new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<String> removeTask(@RequestParam Long id){
		boolean success = ts.deleteTask(id);
		return (success) ?  new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>( HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ValidationDTO exceptionHandler(MethodArgumentNotValidException ex){
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		ValidationDTO validationDTO = new ValidationDTO();
		for(FieldError fe : fieldErrors){
			String field = fe.getField();
			String message = fe.getDefaultMessage();
			validationDTO.addFieldError(field, message);
		}
		return validationDTO;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception ex){
		ex.printStackTrace();
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
