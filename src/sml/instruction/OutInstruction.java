package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class OutInstruction extends Instruction {

    private final RegisterName result;

    public static final String OP_CODE = "out";


    public OutInstruction(String label, RegisterName result) {
        super(label, OP_CODE);
        this.result = result;
    }


    /**
     * Execute the addition instruction, in turn modifying the registers.
     *
     * @param m the machine under which the instruction executes
     */
    @Override
    public int execute(Machine m) {
        System.out.println("The value is " + m.getRegisters().get(result));
        return NORMAL_PROGRAM_COUNTER_UPDATE;

    }

    /**
     * String description of the instruction
     *
     * @return representation of label, opcode and the values in the two registers
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
