package mate.academy.jvteamproject.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringOrListDeserializer extends JsonDeserializer<List<String>> {

    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {

        JsonToken token = p.getCurrentToken();

        if (token == JsonToken.VALUE_STRING) {
            return List.of(p.getValueAsString());
        }

        if (token == JsonToken.START_ARRAY) {
            List<String> list = new ArrayList<>();
            while (p.nextToken() != JsonToken.END_ARRAY) {
                list.add(p.getValueAsString());
            }
            return list;
        }

        return List.of();
    }
}
