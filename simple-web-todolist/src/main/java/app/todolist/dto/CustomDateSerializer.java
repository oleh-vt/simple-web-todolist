package app.todolist.dto;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jg, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		
		SimpleDateFormat df = new SimpleDateFormat(TaskDTO.DATE_PATTERN);
		jg.writeString(df.format(date));
	}
}
