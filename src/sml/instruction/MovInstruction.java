package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;



/**
 * Moves the integer value to the result register. Replacing the old content in register.
 *
 * @author Jay Shamji
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

        if (!(o instanceof MovInstruction that)) return false;

        if (!result.equals(that.result)) return false;
        if (!Integer.valueOf(value).equals(that.value)) return false;


        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, value, label);

    }
}
