package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class DivInstructionTest {

    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        registers.set(EAX, 108);
        registers.set(EBX, 9);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(12, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(ESP, -127);
        registers.set(EDX, 6);
        Instruction instruction = new DivInstruction(null, ESP, EDX);
        instruction.execute(machine);
        Assertions.assertEquals(-21, machine.getRegisters().get(ESP));
    }

    @Test
    void executeValidThree() {
        registers.set(EDI, 143);
        registers.set(EAX, -3);
        Instruction instruction = new DivInstruction(null, EDI, EAX);
        instruction.execute(machine);
        Assertions.assertEquals(-47, machine.getRegisters().get(EDI));
    }

    @Test
    void executeValidFour() {
        registers.set(EDI, -34);
        registers.set(EAX, -2);
        Instruction instruction = new DivInstruction(null, EDI, EAX);
        instruction.execute(machine);
        Assertions.assertEquals(17, machine.getRegisters().get(EDI));
    }

    @Test
    void equalsTrueOne() {
        registers.set(EAX, 10);
        registers.set(EBX, 6);
        Instruction instructionOne = new DivInstruction(null, EAX, EBX);
        Instruction instructionTwo = new DivInstruction(null, EAX, EBX);
        Assertions.assertTrue(instructionOne.equals(instructionTwo));
    }

    @Test
    void equalsTrueTwo() {
        registers.set(ESP, 10);
        registers.set(EDI, 4);
        Instruction instructionOne = new DivInstruction("add1", ESP, EDI);
        Instruction instructionTwo = new DivInstruction("add1", ESP, EDI);
        Assertions.assertTrue(instructionOne.equals(instructionTwo));
    }

    @Test
    void equalsFalseOne() {
        registers.set(EDI, -5);
        registers.set(EBX, 6);
        registers.set(ECX, 9);
        Instruction instructionOne = new DivInstruction(null, EDI, EBX);
        Instruction instructionTwo = new DivInstruction(null, EDI, ECX);
        Assertions.assertFalse(instructionOne.equals(instructionTwo));
    }

    @Test
    void equalsFalseTwo() {
        registers.set(ESP, -66);
        registers.set(EAX, -66);
        Instruction instructionOne = new DivInstruction(null, ESP, EAX);
        Instruction instructionTwo = new DivInstruction("add1", ESP, EAX);
        Assertions.assertFalse(instructionOne.equals(instructionTwo));
    }

    @Test
    void hashTrueOne() {
        registers.set(ESP, -1);
        registers.set(ESI, 11);
        Instruction instructionOne = new DivInstruction(null, ESP, ESI);
        Instruction instructionTwo = new DivInstruction(null, ESP, ESI);
        int hashInstructionOne = instructionOne.hashCode();
        int hashInstructionTwo = instructionTwo.hashCode();
        Assertions.assertTrue(Integer.valueOf(hashInstructionOne).equals(hashInstructionTwo));
    }

    @Test
    void hashFalseOne() {
        registers.set(EDX, 322);
        registers.set(ECX, -1);
        registers.set(EAX, -234);
        Instruction instructionOne = new DivInstruction(null, EDX, ECX);
        Instruction instructionTwo = new DivInstruction(null, EDX, EAX);
        int hashInstructionOne = instructionOne.hashCode();
        int hashInstructionTwo = instructionTwo.hashCode();
        Assertions.assertFalse(Integer.valueOf(hashInstructionOne).equals(hashInstructionTwo));
    }

}
