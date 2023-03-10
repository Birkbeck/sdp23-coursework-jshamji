package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * Represents the instruction that multiplies the contents of result register to contents of source register.
 *
 * Content stored in result register.
 *
 * @author Jay Shamji
 */

public class MulInstruction extends Instruction {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof MulInstruction that)) return false;

        if (!result.equals(that.result)) return false;
        if (!source.equals(that.source)) return false;
        if (getLabelString() != null ? !getLabelString().equals(that.getLabelString()) : that.getLabelString() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, source, label);
    }
}
