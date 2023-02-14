package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class MulInstruction extends Instruction {

    // TODO: write a JavaDoc for the class

    /**
     * @author
     */

    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "mul";

    public MulInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }


    /**
     * Execute the multiplication instruction, in turn modifying the registers.
     *
     * @param m the machine under which the instruction executes
     */
    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 * value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }


    /**
     * String description of the instruction
     *
     * @return representation of label, opcode and the values in the two registers
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }
}
