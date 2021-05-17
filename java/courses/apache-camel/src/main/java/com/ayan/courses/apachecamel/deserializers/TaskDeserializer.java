package com.ayan.courses.apachecamel.deserializers;

import com.ayan.courses.apachecamel.enums.TaskStatusEnum;
import com.ayan.courses.apachecamel.models.Task;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class TaskDeserializer extends StdDeserializer<Task> {

    public TaskDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Task deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Task t = new Task();
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode jn = oc.readTree(jsonParser);
        t.setTitle(jn.get("title").asText());
        if (jn.get("completed").asBoolean()) {
            t.setStatus(TaskStatusEnum.COMPLETED);
        } else {
            t.setStatus(TaskStatusEnum.PROCESSING);
        }
        return t;
    }

}
