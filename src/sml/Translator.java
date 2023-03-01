package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This class turns <b>S</b><b>M</b>al<b>L</b> file into the appropriate labels and instructions.
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author Jay Shamji
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) throws Exception {

        // TODO: Next, use dependency injection to allow this machine class


        String opcode = scan();

        if (line.isEmpty() || line.isBlank()) {
            System.out.println("Unknown instruction: " + opcode);
            return null;
        }

        String instructionClassName = Character.toUpperCase(opcode.charAt(0)) + opcode.substring(1) + "Instruction";
        Class<?> instructionClass = Class.forName("sml.instruction."+instructionClassName);


//        Initialising the instruction constructor
        instructionClass.getEnclosingConstructor();
        Constructor<?> instructionConstructor = switch (instructionClassName) {
            case "MovInstruction" -> instructionClass.getConstructor(String.class, RegisterName.class, int.class);
            case "JnzInstruction" -> instructionClass.getConstructor(String.class, RegisterName.class, String.class);
            case "OutInstruction" -> instructionClass.getConstructor(String.class, RegisterName.class);
            default -> instructionClass.getConstructor(String.class, RegisterName.class, RegisterName.class);
        };

        String r = scan();
        Object s;

//        checks what the last element of the line is by checking what instruction is active
        if (instructionClass.getSimpleName().equals("MovInstruction")) {
            s = Integer.parseInt(line.trim());
        } else if (instructionClass.getSimpleName().equals("JnzInstruction")) {
            s = line.trim();
        } else {
            try {
                s = Registers.Register.valueOf(line.trim());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Illegal register name: " + e.getMessage());
            }
        }

//        creates the instance of the instruction
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


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}