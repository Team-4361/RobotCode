package frc.team4361.season2021.subsystems;

import org.roxbotix.elibs2.robot.components.DigInput;
import org.roxbotix.elibs2.robot.components.TlMotor;
import org.roxbotix.elibs2.subsystem.Subsystem;
import org.roxbotix.elibs2.subsystem.Subsystems;
import frc.team4361.config.Common;
import frc.team4361.config.season2021.M2021;
import frc.team4361.season2021.surrogate.IntakeSurrogate;

/**
 * Subsystem-integrated intake system!
 *
 * <p>
 * All of the code powering this bad boy was written by none other than one man.
 * You know him, you love him - the legendary, the one and only - Jordan
 * Migneault!
 * </p>
 *
 * <p>
 * The intake subsystem, much like the shooter subsystem, utilizes "legacy"
 * code written without the use of elibs2, which is then surrogate-d through
 * the IntakeSurrogate class to adapt the legacy code to the newer code.
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
public class Intake implements Subsystem {
    static {
        Subsystems.registerSubsystem(
                Common.SUB_INTAKE_A,
                new Intake(
                        (int) M2021.get(Common.INTAKE_ROLLER_MOTOR),
                        (int) M2021.get(Common.INTAKE_ACTUATION_MOTOR),
                        (int) M2021.get(Common.INTAKE_TOP_LIMIT),
                        (int) M2021.get(Common.INTAKE_BOTTOM_LIMIT)
                )
        );
    }

    /**
     * The hardware ID of the roller motor.
     */
    private final int rollerMotorId;

    /**
     * The hardware ID of the actuation motor.
     */
    private final int actuationMotorId;

    /**
     * The hardware ID of the top limit switch.
     */
    private final int topLimitId;

    /**
     * The hardware ID of the bottom limit switch.
     */
    private final int bottomLimitId;

    /**
     * The Talon SRX for the roller motor.
     */
    private TlMotor rollerMotor;

    /**
     * The Talon SRX for the actuation motor.
     */
    private TlMotor actuationMotor;

    /**
     * The top limit switch for intake actuation.
     */
    private DigInput topLimit;

    /**
     * The bottom limit switch for intake actuation.
     */
    private DigInput bottomLimit;

    /**
     * The surrogate used for communicating with the CoreIntake.
     */
    private IntakeSurrogate intake;

    /**
     * Create a new intake.
     *
     * @param rollerMotorId the HWID of the roller motor.
     * @param actuationMotorId the HWID of the actuation motor.
     * @param topLimitId the HWID of the top limit switch.
     * @param bottomLimitId the HWID of the bottom limit switch.
     */
    public Intake(final int rollerMotorId,
                  final int actuationMotorId,
                  final int topLimitId,
                  final int bottomLimitId) {
        this.rollerMotorId = rollerMotorId;
        this.actuationMotorId = actuationMotorId;
        this.topLimitId = topLimitId;
        this.bottomLimitId = bottomLimitId;
    }

    /**
     * Get the ID of the roller motor.
     *
     * @return the ID of the roller motor.
     */
    public int getRollerMotorId() {
        return rollerMotorId;
    }

    /**
     * Get the ID of the actuation motor.
     *
     * @return the ID of the actuation motor.
     */
    public int getActuationMotorId() {
        return actuationMotorId;
    }

    /**
     * Get the ID of the top limit switch.
     *
     * @return the ID of the top limit switch.
     */
    public int getTopLimitId() {
        return topLimitId;
    }

    /**
     * Get the ID of the bottom limit switch.
     *
     * @return the ID of the bottom limit switch.
     */
    public int getBottomLimitId() {
        return bottomLimitId;
    }

    /**
     * Get the Talon SRX for the roller motor.
     *
     * @return the Talon SRX for the roller motor.
     */
    public TlMotor getRollerMotor() {
        return rollerMotor;
    }

    /**
     * Get the Talon SRX for the actuation motor.
     *
     * @return the Talon SRX for the actuation motor.
     */
    public TlMotor getActuationMotor() {
        return actuationMotor;
    }

    /**
     * Get the top limit switch's digital input class.
     *
     * @return the top limit switch's digital input class.
     */
    public DigInput getTopLimit() {
        return topLimit;
    }

    /**
     * Get the bottom limit switch's digital input class.
     *
     * @return the bottom limit switch's digital input class.
     */
    public DigInput getBottomLimit() {
        return bottomLimit;
    }

    /**
     * Get the surrogate used for communicating with the intake.
     *
     * <p>
     * I can't think of a reason off the top of my head you'd need to do this.
     * So unless you really do need to use this, I'd suggest you just try to
     * write clean code and use the already available methods.
     * </p>
     *
     * @return the surrogate used for communicating with the intake.
     */
    public IntakeSurrogate getIntake() {
        return intake;
    }

    /**
     * Initialize the intake - exactly the same as any and every other
     * subsystem... or even component, come to think of it.
     */
    @Override
    public void init() {
        rollerMotor = new TlMotor(rollerMotorId);
        actuationMotor = new TlMotor(actuationMotorId);
        topLimit = new DigInput(topLimitId);
        bottomLimit = new DigInput(bottomLimitId);

        rollerMotor.init();
        actuationMotor.init();
        topLimit.init();
        bottomLimit.init();

        intake = new IntakeSurrogate(
                rollerMotor,
                actuationMotor,
                topLimit,
                bottomLimit
        );
    }

    /**
     * Get the name of the subsystem.
     *
     * @return the subsystem's name.
     */
    @Override
    public String getName() {
        return Common.SUB_INTAKE_A.getName();
    }

    /**
     * Start the intake.
     *
     * <p>
     * The intake has two modes/ranges of operation. Those modes/ranges are...
     * <ul>
     *     <li>
     *         Range 1: Positive 1 (maximum) to negative 1 (minimum).
     *     </li>
     *     <li>
     *         Range 2: Positive 100 (maximum) to negative 100 (minimum).
     *     </li>
     * </ul>
     * The intake's mode of operation will automatically be determined based
     * on your input number.
     * </p>
     *
     * @param speed the speed which the intake should go.
     */
    public void startIntake(double speed) {
        speed = Math.abs(speed) > 2 ? speed : speed * 100;
        intake.getIntake().startIntake(speed);
    }

    /**
     * Stop the intake in its tracks. Demolished.
     */
    public void stopIntake() {
        intake.getIntake().stopIntake();
    }

    /**
     * Actuate the intake upwards.
     */
    public void actuateIntakeUp() {
        intake.getIntake().actuateIntakeUp();
    }

    /**
     * Actuate the intake downwards.
     */
    public void actuateIntakeDown() {
        intake.getIntake().intakeActuateDown();
    }

    /**
     * A synonym for the actuateIntakeUp() method.
     */
    public void intakeActuateUp() {
        actuateIntakeUp();
    }

    /**
     * A synonym for the actuateIntakeDown() method.
     */
    public void intakeActuateDown() {
        actuateIntakeDown();
    }
}
