package core.paper.command;

import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;

/**
 * Represents a custom exception type for component-based command errors.
 */
@NullMarked
public class ComponentCommandExceptionType extends SimpleCommandExceptionType {

    /**
     * Represents a custom exception type for component-based command errors.
     *
     * @param component The component describing the exception.
     */
    @SuppressWarnings("UnstableApiUsage")
    public ComponentCommandExceptionType(Component component) {
        super(MessageComponentSerializer.message().serialize(component));
    }
}
