package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class MovInstruction extends Instruction {

    // TODO: write a JavaDoc for the class

    /**
     * @author
     */

    private final RegisterName result;

    private final int value;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName result, int value) {
        super(label, OP_CODE);
        this.result = result;
        this.value = value;
    }

    /**
     * Execute the move instruction, in turn modifying the register.
     *
     * @param m the machine under which the instruction executes
     */

    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + value;
    }
}
