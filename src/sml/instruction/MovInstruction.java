package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class MovInstruction extends Instruction {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof MovInstruction)) return false;

        MovInstruction that = (MovInstruction) o;

        if (!result.equals(that.result)) return false;
        if (!Integer.valueOf(value).equals(that.value)) return false;

//        if (getLabelString() != null) {
//            if (!getLabelString().equals(that.getLabelString())) {
//                return false;
//            }
//        }
//        else if (that.getLabelString() != null)
//            return false;


//        if (!label.equals(that.label)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, value, label);

    }
}
