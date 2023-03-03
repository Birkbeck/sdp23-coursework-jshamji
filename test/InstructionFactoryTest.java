import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.*;
import sml.instruction.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static sml.Registers.Register.*;

public class InstructionFactoryTest {

    private Labels labels;
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        labels = new Labels();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        labels = null;
    }
    @Test
    void createClassValid() {
        String opcode = "add";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        HashMap<Class<?>, String> compareHashMap = new HashMap<>();
        compareHashMap.put(AddInstruction.class, "AddInstruction");
        Assertions.assertEquals(classStringHashMap, compareHashMap);
    }

    @Test
    void createClassValidTwo() {
        String opcode = "div";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        HashMap<Class<?>, String> compareHashMap = new HashMap<>();
        compareHashMap.put(DivInstruction.class, "DivInstruction");
        Assertions.assertEquals(classStringHashMap, compareHashMap);
    }

    @Test
    void createClassValidThree() {
        String opcode = "jnz";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        HashMap<Class<?>, String> compareHashMap = new HashMap<>();
        compareHashMap.put(JnzInstruction.class, "JnzInstruction");
        Assertions.assertEquals(classStringHashMap, compareHashMap);
    }

    @Test
    void createClassValidFour() {
        String opcode = "mov";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        HashMap<Class<?>, String> compareHashMap = new HashMap<>();
        compareHashMap.put(MovInstruction.class, "MovInstruction");
        Assertions.assertEquals(classStringHashMap, compareHashMap);
    }

    @Test
    void createClassValidFive() {
        String opcode = "mul";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        HashMap<Class<?>, String> compareHashMap = new HashMap<>();
        compareHashMap.put(MulInstruction.class, "MulInstruction");
        Assertions.assertEquals(classStringHashMap, compareHashMap);
    }

    @Test
    void createClassValidSix() {
        String opcode = "out";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        HashMap<Class<?>, String> compareHashMap = new HashMap<>();
        compareHashMap.put(OutInstruction.class, "OutInstruction");
        Assertions.assertEquals(classStringHashMap, compareHashMap);
    }

    @Test
    void createClassValidSeven() {
        String opcode = "sub";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        HashMap<Class<?>, String> compareHashMap = new HashMap<>();
        compareHashMap.put(SubInstruction.class, "SubInstruction");
        Assertions.assertEquals(classStringHashMap, compareHashMap);
    }

    @Test
    void createClassExceptionThrownValid() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            String opcode = "sell";
            HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        });
        String expectedMessage = "Instruction class not found: ";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createClassExceptionThrownTwo() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            String opcode = "add1";
            HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        });
        String expectedMessage = "Instruction class not found: ";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createClassExceptionThrownThree() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            String opcode = "jnz ";
            HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        });
        String expectedMessage = "Instruction class not found: ";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createClassExceptionThrownFour() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            String opcode = " out";
            HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        });
        String expectedMessage = "Instruction class not found: ";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createClassExceptionThrownFive() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            String opcode = " sub ";
            HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        });
        String expectedMessage = "Instruction class not found: ";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createClassExceptionThrownSix() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            String opcode = "!mul";
            HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        });
        String expectedMessage = "Instruction class not found: ";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createClassExceptionThrownSeven() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            String opcode = "d iv";
            HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        });
        String expectedMessage = "Instruction class not found: ";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createClassExceptionThrownEight() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            String opcode = "o ut";
            HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);
        });
        String expectedMessage = "Instruction class not found: ";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

//    Checking if constructor created is correct
    @Test
    void createConstructorValid() throws NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        String opcode = "add";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        registers.set(EDX, 6);
        registers.set(ECX, 9);

        Constructor<?> addConstructor = classConstructorHashMap.get(AddInstruction.class);
        AddInstruction addInstruction = (AddInstruction) addConstructor.newInstance(null, EDX,
                ECX);

        AddInstruction comparison = new AddInstruction(null, EDX, ECX);

        Assertions.assertEquals(addInstruction, comparison);

    }

    @Test
    void createConstructorValidTwo() throws NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        String opcode = "div";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        registers.set(EAX, 21);
        registers.set(ECX, 9);

        Constructor<?> divConstructor = classConstructorHashMap.get(DivInstruction.class);
        DivInstruction divInstruction = (DivInstruction) divConstructor.newInstance(null, EAX,
                ECX);

        DivInstruction comparison = new DivInstruction(null, EAX, ECX);

        Assertions.assertEquals(divInstruction, comparison);
    }

    @Test
    void createConstructorValidThree() throws NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        String opcode = "jnz";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        labels.addLabel("j12", 7);
        registers.set(EAX, 21);

        Constructor<?> jnzConstructor = classConstructorHashMap.get(JnzInstruction.class);
        JnzInstruction jnzInstruction = (JnzInstruction) jnzConstructor.newInstance(null, EAX, "j12");

        JnzInstruction comparison = new JnzInstruction(null, EAX, "j12");

        Assertions.assertEquals(jnzInstruction, comparison);
    }

    @Test
    void createConstructorValidFour() throws NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        String opcode = "mov";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        registers.set(EBP, 21);


        Constructor<?> movConstructor = classConstructorHashMap.get(MovInstruction.class);
        MovInstruction movInstruction = (MovInstruction) movConstructor.newInstance(null, EBP,
                2);

        MovInstruction comparison = new MovInstruction(null, EBP, 2);

        Assertions.assertEquals(movInstruction, comparison);
    }

    @Test
    void createConstructorValidFive() throws NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        String opcode = "mul";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        registers.set(EAX, 21);
        registers.set(EDX, 9);

        Constructor<?> mulConstructor = classConstructorHashMap.get(MulInstruction.class);
        MulInstruction mulInstruction = (MulInstruction) mulConstructor.newInstance(null, EAX,
                EDX);

        MulInstruction comparison = new MulInstruction(null, EAX, EDX);

        Assertions.assertEquals(mulInstruction, comparison);
    }

    @Test
    void createConstructorValidSix() throws NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        String opcode = "out";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        registers.set(EDX, 81);

        Constructor<?> outConstructor = classConstructorHashMap.get(OutInstruction.class);
        OutInstruction outInstruction = (OutInstruction) outConstructor.newInstance(null, EDX);

        OutInstruction comparison = new OutInstruction(null, EDX);

        Assertions.assertEquals(outInstruction, comparison);
    }

    @Test
    void createConstructorValidSeven() throws NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        String opcode = "sub";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        registers.set(ECX, 103);
        registers.set(EBP, 1);

        Constructor<?> subConstructor = classConstructorHashMap.get(SubInstruction.class);
        SubInstruction subInstruction = (SubInstruction) subConstructor.newInstance(null, ECX,
                EBP);

        SubInstruction comparison = new SubInstruction(null, ECX, EBP);

        Assertions.assertEquals(subInstruction, comparison);
    }

    @Test
    void createInstructionValid() throws NoSuchMethodException {
        String opcode = "add";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        registers.set(EAX, 65);
        registers.set(EDI, 11);

        String r = "EAX";
        String line = " EDI";

        Instruction instruction = InstructionFactory.createInstruction(classConstructorHashMap, r, null, line);
        AddInstruction comparison = new AddInstruction(null, EAX, EDI);

        Assertions.assertEquals(instruction, comparison);
    }

    @Test
    void createInstructionValidTwo() throws NoSuchMethodException {
        String opcode = "div";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        registers.set(EBP, -11);
        registers.set(EDI, 11);

        String r = "EBP";
        String line = " EDI";

        Instruction instruction = InstructionFactory.createInstruction(classConstructorHashMap, r, null, line);
        DivInstruction comparison = new DivInstruction(null, EBP, EDI);

        Assertions.assertEquals(instruction, comparison);
    }

    @Test
    void createInstructionValidThree() throws NoSuchMethodException {
        String opcode = "jnz";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        labels.addLabel("e1", 7);
        registers.set(ESI, 9);

        String r = "ESI";
        String line = " e1";

        Instruction instruction = InstructionFactory.createInstruction(classConstructorHashMap, r, null, line);
        JnzInstruction comparison = new JnzInstruction(null, ESI, "e1");

        Assertions.assertEquals(instruction, comparison);
    }

    @Test
    void createInstructionValidFour() throws NoSuchMethodException {
        String opcode = "mov";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        registers.set(EDX, -34);
        String r = "EDX";
        String line = " 200";

        Instruction instruction = InstructionFactory.createInstruction(classConstructorHashMap, r, null, line);
        MovInstruction comparison = new MovInstruction(null, EDX, 200);

        Assertions.assertEquals(instruction, comparison);
    }

    @Test
    void createInstructionValidFive() throws NoSuchMethodException {
        String opcode = "mul";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        registers.set(EAX, -11);
        registers.set(EDI, 54);

        String r = "EAX";
        String line = " EDI";

        Instruction instruction = InstructionFactory.createInstruction(classConstructorHashMap, r, null, line);
        MulInstruction comparison = new MulInstruction(null, EAX, EDI);

        Assertions.assertEquals(instruction, comparison);
    }

    @Test
    void createInstructionValidSix() throws NoSuchMethodException {
        String opcode = "out";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        registers.set(EDI, -66);

        String line = " EDI";
        String r = "EDI";

        Instruction instruction = InstructionFactory.createInstruction(classConstructorHashMap, r, null, line);
        OutInstruction comparison = new OutInstruction(null, EDI);

        Assertions.assertEquals(instruction, comparison);
    }

    @Test
    void createInstructionValidSeven() throws NoSuchMethodException {
        String opcode = "sub";
        HashMap<Class<?>, String> classStringHashMap = InstructionFactory.createClassHashmapWithString(opcode);

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = InstructionFactory.
                createClassHashmapWithConstructor(classStringHashMap);

        registers.set(EBX, -11);
        registers.set(EDI, 54);

        String r = "EBX";
        String line = " EDI";

        Instruction instruction = InstructionFactory.createInstruction(classConstructorHashMap, r, null, line);
        SubInstruction comparison = new SubInstruction(null, EBX, EDI);

        Assertions.assertEquals(instruction, comparison);
    }

}
