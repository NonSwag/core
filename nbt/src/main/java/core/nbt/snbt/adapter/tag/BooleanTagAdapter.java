package core.nbt.snbt.adapter.tag;

import com.google.gson.*;
import core.nbt.tag.BooleanTag;
import org.jetbrains.annotations.ApiStatus;

import java.lang.reflect.Type;

@ApiStatus.Internal
@Deprecated(forRemoval = true)
public class BooleanTagAdapter implements JsonSerializer<BooleanTag>, JsonDeserializer<BooleanTag> {
    @Override
    public BooleanTag deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        return new BooleanTag(element.getAsBoolean());
    }

    @Override
    public JsonPrimitive serialize(BooleanTag tag, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(tag.getAsBoolean());
    }
}
