import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Main;
import sml.Registers;

public class ProgramTest {

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

//    Move -88 to register EDI
    @Test
    void runProgram() {
        String[] args = {"test/test1.sml"};
        Main.main(args);
    }

    //    Moving 20 to every Register, then changing ESP to contain 5
    @Test
    void runProgramTwo() {
        String[] args = {"test/test2.sml"};
        Main.main(args);
    }

    //    Factorial of 6 in register EBX
    @Test
    void runProgramThree() {
        String[] args = {"test/test3.sml"};
        Main.main(args);
    }

//    Factorial of 9 in register EBX
    @Test
    void runProgramFour() {
        String[] args = {"test/test4.sml"};
        Main.main(args);
    }
}
