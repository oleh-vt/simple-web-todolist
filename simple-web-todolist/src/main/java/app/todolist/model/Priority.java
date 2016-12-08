package app.todolist.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Priority {
	LOW, MEDIUM, HIGH;
	
	@JsonValue
	public String toName(){
		return name();
	}
	
	@JsonCreator
	public static Priority toEnum(String name){
		return Priority.valueOf(name);
	}
}
