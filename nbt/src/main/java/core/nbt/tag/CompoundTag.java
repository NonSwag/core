package core.nbt.tag;

import core.nbt.NBTOutputStream;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@ToString(callSuper = true)
public class CompoundTag extends ValueTag<Map<String, Tag>> {
    public static final int ID = 10;

    public CompoundTag(@Nullable String name, Map<String, Tag> value) {
        super(name, value);
    }

    public CompoundTag(Map<String, Tag> value) {
        super(value);
    }

    public CompoundTag(String name) {
        super(name, new HashMap<>());
    }

    public CompoundTag() {
        super(new HashMap<>());
    }

    @Override
    public int getTypeId() {
        return ID;
    }

    @Override
    public void write(@NotNull NBTOutputStream outputStream) throws IOException {
        for (var tag : getValue().values()) outputStream.writeTag(tag);
        EscapeTag.INSTANCE.write(outputStream);
    }


    public void set(String name, Tag tag) {
        getValue().put(name, tag);
    }

    public Tag remove(String property) {
        return getValue().remove(property);
    }

    public void set(String name, String value) {
        set(name, new StringTag(name, value));
    }

    public void set(String name, Number number) {
        if (number instanceof Integer value) set(name, new IntTag(name, value));
        else if (number instanceof Float value) set(name, new FloatTag(name, value));
        else if (number instanceof Short value) set(name, new ShortTag(name, value));
        else if (number instanceof Long value) set(name, new LongTag(name, value));
        else if (number instanceof Byte value) set(name, new ByteTag(name, value));
        else set(name, new DoubleTag(name, number.doubleValue()));
    }

    public void set(String property, Boolean value) {
        set(property, new ByteTag(property, value ? (byte) 1 : 0));
    }

    public Set<Map.Entry<String, Tag>> entrySet() {
        return getValue().entrySet();
    }

    public Set<String> keySet() {
        return getValue().keySet();
    }

    public int size() {
        return getValue().size();
    }

    public boolean has(String property) {
        return getValue().containsKey(property);
    }

    public Tag get(String property) {
        return getValue().get(property);
    }

    public <E> ListTag<E> getAsList(String tag) {
        return getValue().get(tag).getAsList();
    }

    public CompoundTag getAsCompound(String tag) {
        return getValue().get(tag).getAsCompound();
    }
}
