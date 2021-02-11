package frc.team4361.testing.op;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4361.config.Common;
import frc.team4361.testing.subsystems.SimpleDrive;
import org.roxbotix.elibs2.op.template.ThreadedController;
import org.roxbotix.elibs2.subsystem.Subsystems;
import org.roxbotix.elibs3.main.RobotMode;

@RobotMode(name = "Simple Tank Testing")
public class TankTest extends ThreadedController {
    private final SimpleDrive drive;

    public TankTest() {
        super(0, 1, 2);

        SimpleDrive.register();

        drive = (SimpleDrive) Subsystems.getSubsystem(Common.DRIVETRAIN_TANK_A.getName());

        fRobotInit.add(
                drive::init
        );

        fTeleopRegular.add(
                () -> drive.drive(
                        joystick1.getX(),
                        joystick1.getY(),
                        joystick2.getX(),
                        joystick2.getY()
                ),
                () -> {
                    SmartDashboard.putNumber("Power L", drive.getFl().get());
                    SmartDashboard.putNumber("Power R", drive.getFr().get());
                }
        );
    }
}
