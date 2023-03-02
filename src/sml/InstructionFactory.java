package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the factory class to choose the instruction that should execute.
 *
 * @author Jay Shamji
 */
public class InstructionFactory {

    /**
     * Creates a hashmap that has concrete Instruction subclass as it's key and String class name as the value.
     *
     * @param opcode the instruction's opcode
     * @return a hashmap containing the class and string class name
     * <p>
     *
     */
    public static HashMap<Class<?>, String> createClassHashmapWithString(String opcode){
        String instructionClassName = Character.toUpperCase(opcode.charAt(0)) + opcode.substring(1) + "Instruction";

        try {
            Class<?> instructionClass = Class.forName("sml.instruction."+instructionClassName);

            HashMap<Class<?>, String> classStringHashMap = new HashMap<>();

            classStringHashMap.put(instructionClass, instructionClassName);

            return classStringHashMap;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Instruction class not found: "+ e.getMessage());
        }
    }

    /**
     * Creates a hashmap that has concrete Instruction subclass as it's key, and it's constructor as the value.
     *
     * @param classStringHashMap containing Instruction class and it's class name as String
     * @return a hashmap containing the class and constructor
     * <p>
     *
     */
    public static HashMap<Class<?>, Constructor<?>> createClassHashmapWithConstructor(HashMap<Class<?>, String> classStringHashMap)
            throws NoSuchMethodException {

        StringBuilder instructionNameBuilder = new StringBuilder();

        Class<?> instructionClass = null;

        for ( Map.Entry<Class<?>, String> entry: classStringHashMap.entrySet() ) {
            instructionClass = entry.getKey();
        }

        instructionNameBuilder.append(classStringHashMap.get(instructionClass));
        String instructionClassName = instructionNameBuilder.toString();

        assert instructionClass != null;
        instructionClass.getEnclosingConstructor();

//        Initialising the instruction constructor
        Constructor<?> instructionConstructor = switch (instructionClassName) {
            case "MovInstruction" -> instructionClass.getConstructor(String.class, RegisterName.class, int.class);
            case "JnzInstruction" -> instructionClass.getConstructor(String.class, RegisterName.class, String.class);
            case "OutInstruction" -> instructionClass.getConstructor(String.class, RegisterName.class);
            default -> instructionClass.getConstructor(String.class, RegisterName.class, RegisterName.class);
        };

        HashMap<Class<?>, Constructor<?>> classConstructorHashMap = new HashMap<>();
        classConstructorHashMap.put(instructionClass, instructionConstructor);

        return classConstructorHashMap;
    }

    /**
     * Helper method that creates the Instruction instance for createInstruction to use
     *
     * @param instructionClass the Instruction class
     * @param instructionConstructor the Instruction constructor
     * @param s the object that is the last word of instruction line
     * @param r the result register
     * @param label the label associated to the address
     * @return The new instruction
     *
     */
    private static Instruction getInstructionInstance(Class<?> instructionClass,
                                                      Constructor<?>instructionConstructor, String label,
                                                      String r, Object s) {
        try {
            if (instructionClass.getSimpleName().equals("OutInstruction")) {
                return (Instruction) instructionConstructor.newInstance(label, Registers.Register.valueOf(r));
            } else {
                return (Instruction) instructionConstructor.newInstance(label, Registers.Register.valueOf(r), s);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error creating instruction: " + e.getMessage());
        }
    }

    /**
     * Creates the instruction instance through the Reflection API
     *
     * @param classConstructorHashMap containing Instruction class and it's constructor as value
     * @param r the result register
     * @param label the label associated to the address
     * @param line last word of the instruction line
     * @return The new instruction
     *
     */
    public static Instruction createInstruction(HashMap<Class<?>, Constructor<?>> classConstructorHashMap, String r,
                                                String label, String line) {

        Class<?> instructionClass = null;
        Constructor<?> instructionConstructor = null;

        for ( Map.Entry<Class<?>, Constructor<?>> entry: classConstructorHashMap.entrySet() ) {
            instructionClass = entry.getKey();
            instructionConstructor = entry.getValue();
        }

//        Checks what the last element of the line is by checking what instruction is active
        Object s;
        assert instructionClass != null;
        if (instructionClass.getSimpleName().equals("MovInstruction")) {
            try {
                s = Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                throw new RuntimeException("Illegal integer format: " + e);
            }
        } else if (instructionClass.getSimpleName().equals("JnzInstruction")) {
            s = line.trim();
        } else {
            try {
                s = Registers.Register.valueOf(line.trim());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Illegal register name: " + e.getMessage());
            }
        }

//        Creates the instance of the instruction
        return getInstructionInstance(instructionClass, instructionConstructor, label, r, s);
    }


}
