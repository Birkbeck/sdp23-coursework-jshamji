package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class SubInstructionTest {

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
        registers.set(EAX, 11);
        registers.set(EBX, 6);
        Instruction instruction = new SubInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValid2() {
        registers.set(EDI, -5);
        registers.set(ECX, -23);
        Instruction instruction = new SubInstruction(null, EDI, ECX);
        instruction.execute(machine);
        Assertions.assertEquals(18, machine.getRegisters().get(EDI));
    }

    @Test
    void executeValid3() {
        registers.set(EDI, -20);
        registers.set(ECX, 23);
        Instruction instruction = new SubInstruction(null, EDI, ECX);
        instruction.execute(machine);
        Assertions.assertEquals(-43, machine.getRegisters().get(EDI));
    }

}
