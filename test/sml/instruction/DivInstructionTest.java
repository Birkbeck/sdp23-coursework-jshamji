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
    void executeValid2() {
        registers.set(ESP, -127);
        registers.set(EDX, 6);
        Instruction instruction = new DivInstruction(null, ESP, EDX);
        instruction.execute(machine);
        Assertions.assertEquals(-21, machine.getRegisters().get(ESP));
    }

    @Test
    void executeValid3() {
        registers.set(EDI, 143);
        registers.set(EAX, -3);
        Instruction instruction = new DivInstruction(null, EDI, EAX);
        instruction.execute(machine);
        Assertions.assertEquals(-47, machine.getRegisters().get(EDI));
    }

    @Test
    void executeValid4() {
        registers.set(EDI, -34);
        registers.set(EAX, -2);
        Instruction instruction = new DivInstruction(null, EDI, EAX);
        instruction.execute(machine);
        Assertions.assertEquals(17, machine.getRegisters().get(EDI));
    }

}
