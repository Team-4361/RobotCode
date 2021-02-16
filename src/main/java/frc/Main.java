package frc;

/**
 * The main robot configuration and access point.
 *
 * <p>
 * This class was originally created to make it easy to configure different
 * robot modes and switch them on-the-fly. By being able to select a different
 * one in a matter of seconds by changing a single string, the amount of time
 * it would take to change a robot's "mode" is cut dramatically.
 * </p>
 *
 * <p>
 * Modes are defined as any instance or extension of TimedRobot. Unfortunately,
 * we can't (don't) scan the whole classpath for anything that extends the
 * lovely TimedRobot - rather, we use an annotation, called RobotMode.
 * </p>
 *
 * <p>
 * If you're unfamiliar with how annotations work, here's a short example.
 * <code>
 * <pre>
 * @RobotMode("demo_mode")
 * public class TankDriveTest extends TimedRobot {
 *     // ... code, yay!
 * }
 * </pre>
 * </code>
 * </p>
 *
 * @author Colin Robertson
 */
public class Main {
    /**
     * The name of the robot's mode of operation.
     *
     * <p>
     * Go read the rest of the JavaDoc for this class to figure out what this
     * means (or why you would even care).
     * </p>
     *
     * <p>
     * 2021's swerve code is named "Swerve Test A". There's more stuff that
     * needs to happen, we need to make different robot modes, whatever - but
     * the name of (hopefully working) 2021 code is "Swerve Test A".
     * </p>
     */
    public static String ROBOT_MODE = "Swerve Test A";
}
