package frc.team4361.testing.op;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import me.wobblyyyy.ezpos.swerve.SwerveModuleTracker;
import me.wobblyyyy.intra.ftc2.utils.Timed;
import me.wobblyyyy.intra.ftc2.utils.async.event.StringEvents;
import org.roxbotix.elibs2.motor.Direction;
import org.roxbotix.elibs2.motor.Motor;
import org.roxbotix.elibs2.motor.MotorConfig;
import org.roxbotix.elibs2.motor.Type;
import org.roxbotix.elibs2.op.template.ThreadedController;
import org.roxbotix.elibs2.thread.PassiveTickingManager;
import org.roxbotix.elibs3.main.RobotMode;

/**
 * Test the core of the robot.
 *
 * <p>
 * This testing code is just a method of me making sure that the simulator
 * is building, compiling, and executing all of the rest of the code. This
 * shouldn't ever actually be deployed to a robot.
 * </p>
 *
 * @author Colin Robertson
 */
@RobotMode(name = "Core Test")
public class CoreTest extends ThreadedController {
    private static final int joystick1_id = 0;
    private static final int joystick2_id = 0;
    private static final int controller_id = 0;

    private MotorConfig motorConfig = new MotorConfig(
            0,
            1,
            1,
            -1,
            1,
            -1,
            1,
            0,
            0,
            Direction.FORWARDS,
            Type.SPARK_BRUSHLESS,
            0,
            0,
            0,
            0
    );
    private Motor motor;

    private SwerveModuleTracker tracker;

    public CoreTest() {
        super(
                joystick1_id,
                joystick2_id,
                controller_id
        );

        preInit.add(
                () -> {
                    motor = new Motor(motorConfig);

                    motor.init();
                }
        );

        fRobotRegular.add(
                () -> {
                    SmartDashboard.putNumber(
                            "joystick 1 x",
                            joystick1.getX()
                    );

                    motor.setPower(joystick1.getX());
                    PassiveTickingManager.addToRun(
                            () -> StringEvents.schedule(
                                    "Motor Main Process",
                                    0,
                                    0,
                                    new Timed() {
                                        @Override
                                        public Runnable close() {
                                            return () -> System.out.println(motor.getPower());
                                        }
                                    },
                                    false
                            )
                    );

                }
        );
    }
}