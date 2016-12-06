package app.todolist.dto;

import java.util.LinkedList;
import java.util.List;

public class ValidationDTO {

	private List<FieldErrorDTO> errors = new LinkedList<>();
	
	public void addFieldError(String field, String msg){
		errors.add(new FieldErrorDTO(field, msg));
	}
	
	public List<FieldErrorDTO> getErrorList(){
		return errors;
	}
}
