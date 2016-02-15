package fi.maailmanloppu.skript.value.skript;

import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;

import fi.maailmanloppu.skript.parser.skript.annotation.TypeData;
import fi.maailmanloppu.skript.util.MethodUtils;
import fi.maailmanloppu.skript.value.ValueType;

/**
 * String/text value type. Translates into Java String.
 * @author bensku
 *
 */
@TypeData(pattern = "(text|string)[s]")
public class StringType implements ValueType {

    @Override
    public boolean accepts(String code) {
        if (code.startsWith("\"")) return true;
        return false;
    }

    @Override
    public Optional<Object> parseValue(String code) {
        return Optional.ofNullable(code.replace("\"", "")); //TODO what about escaping as \"?
    }

    @Override
    public List<Object> parseMultiValue(String code) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void visitMethod(MethodVisitor mv, Object value) {
        new MethodUtils(mv).putToStack(value);
    }

    @Override
    public boolean canVisit(Object obj) {
        return (obj instanceof String);
    }

}
