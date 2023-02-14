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
        //...
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
    void executeValid2() {
        registers.set(EBX, 8);
        Instruction instruction = new MovInstruction(null, EBX, -20);
        instruction.execute(machine);
        Assertions.assertEquals(-20, machine.getRegisters().get(EBX));
    }
}
