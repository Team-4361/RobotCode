package frc.team4361.season2021.subsystems;

import org.roxbotix.elibs2.robot.components.Encoder;
import org.roxbotix.elibs2.robot.components.TlMotor;
import org.roxbotix.elibs2.subsystem.Subsystem;
import org.roxbotix.elibs2.subsystem.Subsystems;
import frc.team4361.config.Common;
import frc.team4361.config.season2021.M2021;
import frc.team4361.season2021.surrogate.ShooterSurrogate;

/**
 * The highest-level abstraction of the Shooter subsystem.
 *
 * <p>
 * This code is run on top of code in the Legacy folder - CoreShooter, to be
 * more specific. I (I meaning Colin) didn't write that code - Jordan did. I
 * wrote some of the comments, but everything else was all him. I looked over
 * it and didn't see any issues - actually, it was even formatted quite nicely,
 * which is certainly nice to see - but if you happen to find any glaring
 * flaws or whatever, you know who to talk to.
 * </p>
 *
 * <p>
 * In order to clarify the meaning of "legacy," I'd just like to say that
 * legacy in no way means outdated or dysfunctional. Prior to 2021, the
 * Roxbotix libraries didn't do much in terms of abstraction. This year,
 * there's a lot more abstraction. Legacy simply means the code doesn't
 * use the new abstractions provided in 2021.
 * </p>
 *
 * @author Colin Robertson
 */
public class Shooter implements Subsystem {
    static {
        Subsystems.registerSubsystem(
                Common.SUB_SHOOTER_A,
                new Shooter(
                        (int) M2021.get(Common.SHOOTER_MOTOR_FLYWHEEL),
                        (int) M2021.get(Common.SHOOTER_MOTOR_ACTUATOR),
                        (int) M2021.get(Common.SHOOTER_ENCODER_FLYWHEEL_A),
                        (int) M2021.get(Common.SHOOTER_ENCODER_FLYWHEEL_B),
                        (int) M2021.get(Common.SHOOTER_ENCODER_ACTUATOR_A),
                        (int) M2021.get(Common.SHOOTER_ENCODER_ACTUATOR_B),
                        (int) M2021.get(Common.SHOOTER_TPR_FLYWHEEL),
                        (int) M2021.get(Common.SHOOTER_TPR_ACTUATOR),
                        (int) M2021.get(Common.SHOOTER_DIAMETER_FLYWHEEL)
                )
        );
    }

    /**
     * The ID of the flywheel motor.
     */
    private final int flywheelId;

    /**
     * The ID of the actuator motor.
     */
    private final int actuatorId;

    /**
     * The flywheel encoder's A channel.
     */
    private final int flywheelEncoderChannelA;

    /**
     * The flywheel encoder's B channel.
     */
    private final int flywheelEncoderChannelB;

    /**
     * The actuator encoder's A channel.
     */
    private final int actuatorEncoderChannelA;

    /**
     * The actuator encoder's B channel.
     */
    private final int actuatorEncoderChannelB;

    /**
     * TPR = Ticks Per Revolution.
     */
    private final int flywheelTpr;

    /**
     * TPR = Ticks Per Revolution.
     */
    private final int actuatorTpr;

    /**
     * The diameter of the flywheel, used in calculating velocity.
     */
    private final int flywheelDiameter;

    /**
     * The flywheel's Talon motor controller.
     */
    private TlMotor flywheel;

    /**
     * The actuator's Talon motor controller.
     */
    private TlMotor actuator;

    /**
     * The flywheel's encoder.
     */
    private Encoder flywheelEncoder;

    /**
     * The actuator's encoder.
     */
    private Encoder actuatorEncoder;

    /**
     * The ShooterSurrogate instance, used to interact between the lower
     * level ShooterCore and the more abstract Shooter.
     */
    private ShooterSurrogate shooter;

    public Shooter(final int flywheelId,
                   final int actuatorId,
                   final int flywheelEncoderChannelA,
                   final int flywheelEncoderChannelB,
                   final int actuatorEncoderChannelA,
                   final int actuatorEncoderChannelB,
                   final int flywheelTpr,
                   final int actuatorTpr,
                   final int flywheelDiameter) {
        this.flywheelId = flywheelId;
        this.actuatorId = actuatorId;
        this.flywheelEncoderChannelA = flywheelEncoderChannelA;
        this.flywheelEncoderChannelB = flywheelEncoderChannelB;
        this.actuatorEncoderChannelA = actuatorEncoderChannelA;
        this.actuatorEncoderChannelB = actuatorEncoderChannelB;
        this.flywheelTpr = flywheelTpr;
        this.actuatorTpr = actuatorTpr;
        this.flywheelDiameter = flywheelDiameter;
    }

    /**
     * Initialize the shooter subsystem.
     *
     * <p>
     * This works exactly as you'd expect it to. How shocking.
     * </p>
     */
    @Override
    public void init() {
        flywheel = new TlMotor(flywheelId);
        flywheelEncoder = new Encoder(
                flywheelEncoderChannelA,
                flywheelEncoderChannelB,
                false
        );

        actuator = new TlMotor(actuatorId);
        actuatorEncoder = new Encoder(
                actuatorEncoderChannelA,
                actuatorEncoderChannelB,
                false
        );

        flywheel.init();
        flywheelEncoder.init();

        actuator.init();
        actuatorEncoder.init();

        shooter = new ShooterSurrogate(
                flywheel,
                actuator,
                flywheelEncoder,
                actuatorEncoder,
                flywheelTpr,
                actuatorTpr,
                flywheelDiameter
        );
    }

    /**
     * Get the name of the subsystem.
     *
     * @return the common name for a shooter subsystem.
     */
    @Override
    public String getName() {
        return Common.SUB_SHOOTER_A.getName();
    }

    /**
     * Shoot the shooter? Shooter the shoot? I don't know... something like
     * that.
     *
     * <p>
     * You're currently reading code written by Colin. The code that actually
     * controls the shooter itself was written by Jordan. Jordan has been on an
     * FRC team for 3 years. Colin has not. Point is, we express inputs for
     * speeds a bit differently. I've always used a range of -1 to +1, but he's
     * using a range of -100 to +100. So you can put either in. Any number above
     * 2 is automatically considered to be in the Jordan range, and any number
     * below 2 (in terms of absolute value, that is) is considered to be in the
     * Colin range. Yay! Teamwork!
     * </p>
     *
     * @param speed the speed at which the shooter should be shot at. Read the
     *              rest of the JavaDoc to learn more.
     */
    public void shoot(double speed) {
        speed = Math.abs(speed) > 2 ? speed : speed * 100;
        shooter.getCoreShooter().shoot(speed);
    }

    /**
     * Stop the shooter.
     */
    public void stopShooting() {
        shooter.getCoreShooter().stopShooting();
    }

    /**
     * Get the velocity of the shooter.
     *
     * <p>
     * Last I heard, Jordan was unsure of whether or not this velocity code
     * works. We should follow up on that and check it for accuracy.
     * </p>
     *
     * @return the shooter's velocity.
     */
    public double getVelocity() {
        return shooter.getCoreShooter().getVelocity();
    }
}
