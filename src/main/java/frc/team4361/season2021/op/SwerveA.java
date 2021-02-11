package frc.team4361.season2021.op;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4361.config.Common;
import frc.team4361.config.season2021.M2021;
import frc.team4361.season2021.subsystems.Intake;
import frc.team4361.season2021.subsystems.Shooter;
import frc.team4361.season2021.subsystems.Storage;
import frc.team4361.season2021.subsystems.SwerveDTV2;
import org.roxbotix.elibs2.impl.AutoTrackingBackgroundRunnable;
import org.roxbotix.elibs2.op.template.ThreadedController;
import org.roxbotix.elibs2.subsystem.Subsystem;
import org.roxbotix.elibs2.subsystem.Subsystems;
import org.roxbotix.elibs3.main.RobotMode;

@RobotMode(name = "Swerve Test A")
public class SwerveA extends ThreadedController {
    private static final int JOYSTICK1_ID = (int) M2021.get(Common.JOYSTICK_L_A);
    private static final int JOYSTICK2_ID = (int) M2021.get(Common.JOYSTICK_R_A);
    private static final int CONTROLLER_ID = (int) M2021.get(Common.XBOX_A);

    private static final String SWERVE = Common.SUB_SWERVE_B.getName();
    private static final String SHOOTER = Common.SUB_SHOOTER_A.getName();
    private static final String INTAKE = Common.SUB_INTAKE_A.getName();
    private static final String STORAGE = Common.SUB_STORAGE_A.getName();

    private Field2d field = new Field2d();

    private final SwerveDTV2 swerve;
//    private final Shooter shooter;
//    private final Intake intake;
//    private final Storage storage;

    private final Runnable init = new Runnable() {
        @Override
        public void run() {
            swerve.init();
//            shooter.init();
//            intake.init();
//            storage.init();
        }
    };

    public SwerveA() {
        super(
                JOYSTICK1_ID,
                JOYSTICK2_ID,
                CONTROLLER_ID
        );

        SwerveDTV2.register();

        swerve = (SwerveDTV2) Subsystems.getSubsystem(SWERVE);
//        shooter = (Shooter) Subsystems.getSubsystem(SHOOTER);
//        intake = (Intake) Subsystems.getSubsystem(INTAKE);
//        storage = (Storage) Subsystems.getSubsystem(STORAGE);

        preInit.add(
                init,
                () -> {
                    threadManager.registerThread(new AutoTrackingBackgroundRunnable());
                    threadManager.init();
                }
        );

        fTeleopInit.add(
                () -> SmartDashboard.putData("Field", field)
        );

        fTeleopPeriodic.add(
                () -> {
                    Rotation2d r = Rotation2d.fromDegrees(swerve.getSwerve().getTracker().getAngle());
                    swerve.drive(
                            joystick1.getX(),
                            joystick1.getY(),
                            joystick2.getX(),
                            joystick2.getY()
                    );
                    field.setRobotPose(new Pose2d(
                            swerve.getSwerve().getTracker().getX(),
                            swerve.getSwerve().getTracker().getY(),
                            r
                    ));

                    SmartDashboard.putNumber(
                            "FR DRIVE",
                            swerve.fr.getDrive().get()
                    );
                    SmartDashboard.putNumber(
                            "FR TURN",
                            swerve.fr.getTurn().get()
                    );
                    SmartDashboard.putNumber(
                            "FL DRIVE",
                            swerve.fl.getDrive().get()
                    );
                    SmartDashboard.putNumber(
                            "FL TURN",
                            swerve.fl.getTurn().get()
                    );
                    SmartDashboard.putNumber(
                            "BR DRIVE",
                            swerve.br.getDrive().get()
                    );
                    SmartDashboard.putNumber(
                            "BR TURN",
                            swerve.br.getTurn().get()
                    );
                    SmartDashboard.putNumber(
                            "BL DRIVE",
                            swerve.bl.getDrive().get()
                    );
                    SmartDashboard.putNumber(
                            "BL TURN",
                            swerve.bl.getTurn().get()
                    );
                }
        );
    }
}
