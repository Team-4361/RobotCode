package frc.team4361.testing.op;

import frc.team4361.testing.subsystems.PfMotorGen;
import me.wobblyyyy.pathfinder.api.Pathfinder;
import me.wobblyyyy.pathfinder.config.PathfinderConfig;
import me.wobblyyyy.pathfinder.core.Followers;
import me.wobblyyyy.pathfinder.drive.meccanum.Meccanum;
import me.wobblyyyy.pathfinder.frc.PfEncoder;
import me.wobblyyyy.pathfinder.frc.PfMotor;
import me.wobblyyyy.pathfinder.geometry.HeadingPoint;
import me.wobblyyyy.pathfinder.map.Map;
import me.wobblyyyy.pathfinder.maps.frc.EmptyFRC;
import me.wobblyyyy.pathfinder.tracking.Tracker;
import me.wobblyyyy.pathfinder.tracking.threeWheel.ThreeWheelChassisTracker;
import me.wobblyyyy.pathfinder.util.RobotProfile;
import org.roxbotix.elibs2.op.template.ThreadedController;
import org.roxbotix.elibs3.main.RobotMode;

import java.util.ArrayList;

@RobotMode(name = "MeccanumPathfinderTest")
public class MeccanumPathfinderTest extends ThreadedController {
    PfMotor fr = PfMotorGen.getMotor(0, 1, 2);
    PfMotor fl = PfMotorGen.getMotor(4, 3, 4);
    PfMotor br = PfMotorGen.getMotor(7, 5, 6);
    PfMotor bl = PfMotorGen.getMotor(10, 7, 8);

    PfEncoder left = new PfEncoder(10, 20, false, 1024);
    PfEncoder right = new PfEncoder(11, 22, false, 1024);
    PfEncoder middle = new PfEncoder(12, 21, false, 1024);

    Tracker tracker = new ThreeWheelChassisTracker(
            left,
            right,
            middle,
            5,
            5,
            5,
            5
    );
    Meccanum meccanum = new Meccanum(
            fr,
            fl,
            br,
            bl
    );
    RobotProfile profile = new RobotProfile(
            1,
            1,
            10,
            10,
            10,
            60
    );
    Map map = new EmptyFRC();
    PathfinderConfig config = new PathfinderConfig(
            tracker,
            320,
            650,
            2,
            24,
            24,
            24,
            24,
            profile,
            meccanum,
            map,
            Followers.LINEAR,
            true,
            true,
            true
    );
    Pathfinder pathfinder;

    public MeccanumPathfinderTest() {
        super(0, 1, 2);

        pathfinder = new Pathfinder(config);

        fRobotInit.add(
                () -> {
                    fr.init();
                    fl.init();
                    br.init();
                    bl.init();
                }
        );

        fRobotInit.add(
                () -> {
//                    new Thread(() -> {
//                    pathfinder.goToPosition(new HeadingPoint(
//                            20,
//                            20,
//                            0
//                    ));
//                    pathfinder.followPath(new ArrayList<>() {{
//                        add(new HeadingPoint(
//                                20,
//                                20,
//                                20
//                        ));
//                        add(new HeadingPoint(
//                                30,
//                                30,
//                                30
//                        ));
//                    }});
//                    pathfinder.lock();

//                    System.out.println(pathfinder.getPosition());
//                    }).start();
                }
        );

        fRobotRegular.add(
                () -> new Thread(() -> {
                    int i = 0;

                    while (true) {
                        Thread.onSpinWait();

                        if (i == 100) {
                            i = 0;
//                            System.out.println(pathfinder.getPosition());
                            System.out.println(fr.getPower());
                            pathfinder.lock();
                        } else {
                            i++;
                        }

                        tracker.update();
                        left.updateEncoder(i / 2);
                        right.updateEncoder(i / 4);
                        middle.updateEncoder(i / 8);
                    }
                }).start()
        );
    }
}
