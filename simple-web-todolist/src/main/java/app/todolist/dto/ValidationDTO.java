package app.todolist.dto;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;

public class ValidationDTO {

	@Getter
	private List<FieldErrorDTO> errors = new LinkedList<>();
	
	public void addFieldError(String field, String msg){
		errors.add(new FieldErrorDTO(field, msg));
	}
}
