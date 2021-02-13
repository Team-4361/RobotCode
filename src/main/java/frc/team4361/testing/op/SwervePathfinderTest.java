package frc.team4361.testing.op;

import frc.team4361.testing.subsystems.PfMotorGen;
import me.wobblyyyy.pathfinder.api.Pathfinder;
import me.wobblyyyy.pathfinder.config.PathfinderConfig;
import me.wobblyyyy.pathfinder.core.Followers;
import me.wobblyyyy.pathfinder.drive.swerve.Swerve;
import me.wobblyyyy.pathfinder.frc.EncodedMotor;
import me.wobblyyyy.pathfinder.maps.frc.EmptyFRC;
import me.wobblyyyy.pathfinder.tracking.swerve.SwerveChassisTracker;
import me.wobblyyyy.pathfinder.util.RobotProfile;
import org.roxbotix.elibs2.op.template.ThreadedController;
import org.roxbotix.elibs3.main.RobotMode;

@RobotMode(name = "SwervePathfinderTest")
public class SwervePathfinderTest extends ThreadedController {
    EncodedMotor fr_drive = PfMotorGen.getEncodedMotor(0, 0, 0, false);
    EncodedMotor fl_drive = PfMotorGen.getEncodedMotor(0, 0, 0, false);
    EncodedMotor br_drive = PfMotorGen.getEncodedMotor(0, 0, 0, false);
    EncodedMotor bl_drive = PfMotorGen.getEncodedMotor(0, 0, 0, false);

    EncodedMotor fr_turn = PfMotorGen.getEncodedMotor(0, 0, 0, false);
    EncodedMotor fl_turn = PfMotorGen.getEncodedMotor(0, 0, 0, false);
    EncodedMotor br_turn = PfMotorGen.getEncodedMotor(0, 0, 0, false);
    EncodedMotor bl_turn = PfMotorGen.getEncodedMotor(0, 0, 0, false);

    public SwervePathfinderTest() {
        super(0, 0, 0);

        SwerveChassisTracker tracker = new SwerveChassisTracker(
                fr_turn,
                fr_drive,
                fl_turn,
                fl_drive,
                br_turn,
                bl_drive,
                bl_turn,
                bl_drive,
                100,
                100,
                100
        );

        Swerve swerve = new Swerve(
                fr_drive,
                fl_drive,
                br_drive,
                bl_drive,
                fr_turn,
                fl_turn,
                br_turn,
                bl_turn,
                fr_drive,
                fl_drive,
                br_drive,
                bl_drive,
                fr_turn,
                fl_turn,
                br_turn,
                bl_turn
        );

        PathfinderConfig config = new PathfinderConfig(
                tracker,
                100,
                100,
                2,
                32,
                32,
                32,
                32,
                new RobotProfile(
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        0.0
                ),
                swerve,
                new EmptyFRC(),
                Followers.SWERVE,
                true,
                true,
                true
        );

        Pathfinder pathfinder = new Pathfinder(config);

        fRobotInit.add(() -> {
            fr_drive.getPfMotor().init();
            fl_drive.getPfMotor().init();
            br_drive.getPfMotor().init();
            bl_drive.getPfMotor().init();

            fr_turn.getPfMotor().init();
            fl_turn.getPfMotor().init();
            br_turn.getPfMotor().init();
            bl_turn.getPfMotor().init();
        });
    }
}
