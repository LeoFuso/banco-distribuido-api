package com.leofuso.academico.cd.bancod.api.application.communication.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocaleDateTimeStdDeserializer extends StdDeserializer<LocalDateTime> {

    public LocaleDateTimeStdDeserializer() {
        this(null);
    }

    private LocaleDateTimeStdDeserializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        String valueAsString = jp.getValueAsString();

        if (valueAsString == null) {
            return null;
        }

        long epochMilli = Long.parseLong(valueAsString);
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
    }

}
