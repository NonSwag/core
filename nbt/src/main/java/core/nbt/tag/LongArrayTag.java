package core.nbt.tag;

import core.nbt.NBTOutputStream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class LongArrayTag implements Tag {
    public static final int ID = 12;
    private final @Nullable String name;
    private long[] longs;

    @Override
    public int getTypeId() {
        return ID;
    }

    @Override
    public void write(@NotNull NBTOutputStream outputStream) throws IOException {
        outputStream.writeLong(getLongs().length);
        for (var l : getLongs()) outputStream.writeLong(l);
    }
}
