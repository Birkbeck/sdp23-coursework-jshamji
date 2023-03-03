package sml.instruction;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;
public class MovInstructionTest {

    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        registers.set(EAX, 3);
        Instruction instruction = new MovInstruction(null, EAX, 13);
        instruction.execute(machine);
        Assertions.assertEquals(13, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EBX, 8);
        Instruction instruction = new MovInstruction(null, EBX, -20);
        instruction.execute(machine);
        Assertions.assertEquals(-20, machine.getRegisters().get(EBX));
    }

    @Test
    void equalsTrueOne() {
        registers.set(EAX, 10);
        Instruction instructionOne = new MovInstruction(null, EAX, -20);
        Instruction instructionTwo = new MovInstruction(null, EAX, -20);
        Assertions.assertTrue(instructionOne.equals(instructionTwo));
    }

    @Test
    void equalsTrueTwo() {
        registers.set(ESP, 10);
        Instruction instructionOne = new MovInstruction("mov1", ESP, 5);
        Instruction instructionTwo = new MovInstruction("mov1", ESP, 5);
        Assertions.assertTrue(instructionOne.equals(instructionTwo));
    }

    @Test
    void equalsFalseOne() {
        registers.set(EDI, 8);
        registers.set(ESP, 10);
        Instruction instructionOne = new MovInstruction("mov1", EDI, 5);
        Instruction instructionTwo = new MovInstruction("mov1", ESP, 5);
        Assertions.assertFalse(instructionOne.equals(instructionTwo));
    }

    @Test
    void equalsFalseTwo() {
        registers.set(EDI, 8);
        registers.set(EDI, 10);
        Instruction instructionOne = new MovInstruction("mov1", EDI, 199);
        Instruction instructionTwo = new MovInstruction("mov1", EDI, 5);
        Assertions.assertFalse(instructionOne.equals(instructionTwo));
    }

    @Test
    void equalsFalseThree() {
        registers.set(EDI, 8);
        registers.set(EDI, 10);
        Instruction instructionOne = new MovInstruction("mov1", EDI, 199);
        Instruction instructionTwo = new MovInstruction(null, EDI, 5);
        Assertions.assertFalse(instructionOne.equals(instructionTwo));
    }

    @Test
    void hashTrueOne() {
        registers.set(EDI, 1);
        Instruction instructionOne = new MovInstruction(null, EDI, 5);
        Instruction instructionTwo = new MovInstruction(null, EDI, 5);
        int hashInstructionOne = instructionOne.hashCode();
        int hashInstructionTwo = instructionTwo.hashCode();
        Assertions.assertTrue(Integer.valueOf(hashInstructionOne).equals(hashInstructionTwo));
    }

    @Test
    void hashFalseOne() {
        registers.set(EDI, 78);
        registers.set(EAX, -234);
        Instruction instructionOne = new MovInstruction(null, EDI, 2);
        Instruction instructionTwo = new MovInstruction(null, EAX, 2);
        int hashInstructionOne = instructionOne.hashCode();
        int hashInstructionTwo = instructionTwo.hashCode();
        Assertions.assertFalse(Integer.valueOf(hashInstructionOne).equals(hashInstructionTwo));
    }

    @Test
    void hashFalseTwo() {
        registers.set(EAX, -234);
        Instruction instructionOne = new MovInstruction("mov1", EAX, 2);
        Instruction instructionTwo = new MovInstruction(null, EAX, 2);
        int hashInstructionOne = instructionOne.hashCode();
        int hashInstructionTwo = instructionTwo.hashCode();
        Assertions.assertFalse(Integer.valueOf(hashInstructionOne).equals(hashInstructionTwo));
    }

    @Test
    void hashFalseThree() {
        registers.set(EAX, 254);
        Instruction instructionOne = new MovInstruction("mov1", EAX, 2);
        Instruction instructionTwo = new MovInstruction("mov1", EAX, 6);
        int hashInstructionOne = instructionOne.hashCode();
        int hashInstructionTwo = instructionTwo.hashCode();
        Assertions.assertFalse(Integer.valueOf(hashInstructionOne).equals(hashInstructionTwo));
    }
}
