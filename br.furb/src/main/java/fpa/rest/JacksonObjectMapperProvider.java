package fpa.rest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Provider
public class JacksonObjectMapperProvider implements ContextResolver<ObjectMapper> {

    final ObjectMapper defaultObjectMapper;

    public JacksonObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }

    private static ObjectMapper createDefaultMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, false);
        
        SimpleModule testModule = new SimpleModule("LocalDate", new Version(1, 0, 0, null, null, null))
        		   .addSerializer(Exception.class, new JsonSerializer<Exception>() {

					@Override
					public void serialize(Exception arg0, JsonGenerator arg1, SerializerProvider arg2)
							throws IOException, JsonProcessingException {
						arg1.writeString(arg0.getMessage());
					}} )
        		   .addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {

   					@Override
   					public void serialize(LocalDate arg0, JsonGenerator arg1, SerializerProvider arg2)
   							throws IOException, JsonProcessingException {
   						arg1.writeObject(arg0.toString());
   					}} )
        		   .addDeserializer(LocalDate.class, new JsonDeserializer<LocalDate>() {

					@Override
					public LocalDate deserialize(JsonParser arg0, DeserializationContext arg1)
							throws IOException, JsonProcessingException {
						return ZonedDateTime.parse(arg0.getValueAsString()).toLocalDate();
					}
				});
        mapper.registerModule(testModule);

        return mapper;
    }

}