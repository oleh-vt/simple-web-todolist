package app.todolist.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;

@MappedSuperclass
abstract class AbstractEntity {
	@Id
	@GeneratedValue
	@Getter
	private Long id;
}
