
import org.junit.jupiter.api.Test;
import sml.Main;

public class ProgramTest {

//    Move -88 to register EDI
    @Test
    void runProgram() {
        String[] args = {"test/test1.sml"};
        Main.main(args);
    }
//    Unknown instruction for first two lines, then moving 20 to every register, then changing ESP to contain 5
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
