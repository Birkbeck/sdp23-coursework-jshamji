package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class AddInstructionTest {
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
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void equalsTrueOne() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instructionOne = new AddInstruction(null, EAX, EBX);
    Instruction instructionTwo = new AddInstruction(null, EAX, EBX);
    Assertions.assertTrue(instructionOne.equals(instructionTwo));
  }

  @Test
  void equalsTrueTwo() {
    registers.set(EBP, 10);
    registers.set(EDI, 4);
    Instruction instructionOne = new AddInstruction("add1", EBP, EDI);
    Instruction instructionTwo = new AddInstruction("add1", EBP, EDI);
    Assertions.assertTrue(instructionOne.equals(instructionTwo));
  }

  @Test
  void equalsFalseOne() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    registers.set(ECX, 9);
    Instruction instructionOne = new AddInstruction(null, EAX, EBX);
    Instruction instructionTwo = new AddInstruction(null, EAX, ECX);
    Assertions.assertFalse(instructionOne.equals(instructionTwo));
  }

  @Test
  void hashTrueOne() {
    registers.set(EDX, 6);
    registers.set(ECX, 9);
    Instruction instructionOne = new AddInstruction(null, EDX, ECX);
    Instruction instructionTwo = new AddInstruction(null, EDX, ECX);
    int hashInstructionOne = instructionOne.hashCode();
    int hashInstructionTwo = instructionTwo.hashCode();
    Assertions.assertTrue(Integer.valueOf(hashInstructionOne).equals(hashInstructionTwo));
  }

  @Test
  void hashFalseOne() {
    registers.set(EDX, 6);
    registers.set(ECX, 9);
    registers.set(EAX, -234);
    Instruction instructionOne = new AddInstruction(null, EDX, ECX);
    Instruction instructionTwo = new AddInstruction(null, EDX, EAX);
    int hashInstructionOne = instructionOne.hashCode();
    int hashInstructionTwo = instructionTwo.hashCode();
    Assertions.assertFalse(Integer.valueOf(hashInstructionOne).equals(hashInstructionTwo));
  }

}