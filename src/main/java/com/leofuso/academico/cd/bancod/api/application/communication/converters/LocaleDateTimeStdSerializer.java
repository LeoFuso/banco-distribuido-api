package com.leofuso.academico.cd.bancod.api.application.communication.converters;

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

    private LocaleDateTimeStdSerializer(final Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(final LocalDateTime value,
                          final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException {

        if (value == null) {
            jsonGenerator.writeString(String.valueOf(0L));
            return;
        }

        final long epochMilli = value
                .atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli();

        jsonGenerator.writeString(String.valueOf(epochMilli));

    }
}
