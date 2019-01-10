package com.msy.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * @description:
 * @date:
 * @author:wuyanzhong
 */

public class DateSerializer extends JsonSerializer<Date>
{
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException
	{
		jgen.writeString(formatter.format(value));
	}
}
