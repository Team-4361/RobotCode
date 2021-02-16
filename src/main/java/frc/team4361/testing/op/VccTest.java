package frc.team4361.testing.op;

import org.roxbotix.elibs2.motor.*;
import org.roxbotix.elibs3.main.RobotMode;

@RobotMode(name = "Simple Swerve Testing")
public class VccTest {
    private final SimulatedMotor fr;

    public VccTest() {
        fr = new SimulatedMotor(new MotorConfig(
                0,
                1.0,
                1.0,
                -1.0,
                1.0,
                -1.0,
                1.0,
                0.0,
                0.0,
                Direction.FORWARDS,
                Type.SPARK_BRUSHLESS,
                1,
                2,
                0,
                128
        ));

        fr.init();
    }
}
