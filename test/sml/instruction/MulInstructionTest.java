package sml.instruction;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;
public class MulInstructionTest {

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
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EDI, -17);
        registers.set(EBP, 8);
        Instruction instruction = new MulInstruction(null, EDI, EBP);
        instruction.execute(machine);
        Assertions.assertEquals(-136, machine.getRegisters().get(EDI));
    }

    @Test
    void equalsTrueOne() {
        registers.set(EAX, 10);
        registers.set(EBX, 6);
        Instruction instructionOne = new MulInstruction(null, EAX, EBX);
        Instruction instructionTwo = new MulInstruction(null, EAX, EBX);
        Assertions.assertTrue(instructionOne.equals(instructionTwo));
    }

    @Test
    void equalsTrueTwo() {
        registers.set(EAX, 34);
        registers.set(EBX, 4);
        Instruction instructionOne = new MulInstruction("add1", EAX, EBX);
        Instruction instructionTwo = new MulInstruction("add1", EAX, EBX);
        Assertions.assertTrue(instructionOne.equals(instructionTwo));
    }

    @Test
    void equalsFalseOne() {
        registers.set(ESP, 23);
        registers.set(EBX, 6);
        registers.set(ECX, 9);
        Instruction instructionOne = new MulInstruction(null, ESP, EBX);
        Instruction instructionTwo = new MulInstruction(null, ESP, ECX);
        Assertions.assertFalse(instructionOne.equals(instructionTwo));
    }

    @Test
    void equalsFalseTwo() {
        registers.set(ESP, 23);
        registers.set(EBX, 6);
        Instruction instructionOne = new MulInstruction("mul1", ESP, EBX);
        Instruction instructionTwo = new MulInstruction(null, ESP, EBX);
        Assertions.assertFalse(instructionOne.equals(instructionTwo));
    }

    @Test
    void hashTrueOne() {
        registers.set(EDI, 1);
        registers.set(ESI, 23);
        Instruction instructionOne = new MulInstruction(null, EDI, ESI);
        Instruction instructionTwo = new MulInstruction(null, EDI, ESI);
        int hashInstructionOne = instructionOne.hashCode();
        int hashInstructionTwo = instructionTwo.hashCode();
        Assertions.assertTrue(Integer.valueOf(hashInstructionOne).equals(hashInstructionTwo));
    }

    @Test
    void hashFalseOne() {
        registers.set(EDI, 78);
        registers.set(ECX, 93);
        registers.set(EAX, -234);
        Instruction instructionOne = new MulInstruction(null, EDI, ECX);
        Instruction instructionTwo = new MulInstruction(null, EDI, EAX);
        int hashInstructionOne = instructionOne.hashCode();
        int hashInstructionTwo = instructionTwo.hashCode();
        Assertions.assertFalse(Integer.valueOf(hashInstructionOne).equals(hashInstructionTwo));
    }

    @Test
    void hashFalseTwo() {
        registers.set(EBP, 21);
        registers.set(ESI, 40);
        Instruction instructionOne = new MulInstruction("sub1", EBP, ESI);
        Instruction instructionTwo = new MulInstruction("sub2", EBP, ESI);
        int hashInstructionOne = instructionOne.hashCode();
        int hashInstructionTwo = instructionTwo.hashCode();
        Assertions.assertFalse(Integer.valueOf(hashInstructionOne).equals(hashInstructionTwo));
    }


}
