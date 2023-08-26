package core.nbt.tag;

import core.nbt.NBTOutputStream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StringTag implements Tag {
    public static final int ID = 8;
    private final @Nullable String name;
    private String value;

    @Override
    public int getTypeId() {
        return ID;
    }

    @Override
    public void write(@NotNull NBTOutputStream outputStream) throws IOException {
        var bytes = getValue().getBytes(StandardCharsets.UTF_8);
        outputStream.writeShort(bytes.length);
        outputStream.write(bytes);
    }
}
