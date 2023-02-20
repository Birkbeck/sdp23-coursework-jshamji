package sml.instruction;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.RegisterName;

import java.util.Map;
import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */
public class JnzInstruction extends Instruction {

    private final RegisterName result;

    private final String executeLabel;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName result, String executeLabel) {
        super(label, OP_CODE);
        this.result = result;
        this.executeLabel = executeLabel;

    }


    /**
     * Execute the jump if not zero instruction.
     *
     * @param m the machine under which the instruction executes
     */
    @Override
    public int execute(Machine m) {
        int contentOfRegister = m.getRegisters().get(result);

        if (contentOfRegister != 0) {
            int address = m.getLabels().getAddress(executeLabel);
            return address;
        }
        else return NORMAL_PROGRAM_COUNTER_UPDATE;

    }


    /**
     * String description of the instruction
     *
     * @return representation of label, opcode and the values in the two registers
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + executeLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof JnzInstruction)) return false;

        JnzInstruction that = (JnzInstruction) o;

        if (!result.equals(that.result)) return false;
        if (!executeLabel.equals(that.executeLabel)) return false;

        if (getLabelString() != null ? !getLabelString().equals(that.getLabelString()) : that.getLabelString() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, executeLabel, label);
    }
}
