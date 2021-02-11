package frc.team4361.testing.op;

import me.wobblyyyy.pathfinder.api.Pathfinder;
import me.wobblyyyy.pathfinder.config.PathfinderConfig;
import org.roxbotix.elibs2.motor.*;
import org.roxbotix.elibs2.op.template.ThreadedController;
import org.roxbotix.elibs3.main.RobotMode;

@RobotMode(name = "VccTest")
public class VccTest extends ThreadedController {
    private final SimulatedMotor fr;

    public VccTest() {
        super(0, 1, 2);

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

        fTeleopRegular.add(
                () -> {
                    System.out.println(fr.getEncoder().getCount());
                }
        );
    }
}
