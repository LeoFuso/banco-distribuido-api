package com.leofuso.academico.cd.bancodistribuido.application.communication.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocaleDateTimeStdSerializer extends StdSerializer<LocalDateTime> {

    public LocaleDateTimeStdSerializer() {
        this(null);
    }

    private LocaleDateTimeStdSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if (value == null) {
            jsonGenerator.writeString(String.valueOf(0L));
            return;
        }

        long epochMilli = value
                .atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli();

        jsonGenerator.writeString(String.valueOf(epochMilli));

    }
}
