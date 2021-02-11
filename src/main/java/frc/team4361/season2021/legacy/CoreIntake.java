package frc.team4361.season2021.legacy;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;

/**
 * This is the code for the intake mechanism on Team 4361's 2021 robot.
 *
 * <p><i>
 * Changes, 1/23/2021:
 * <ul>
 *     <li>
 *         Add JavaDocs to all public and private fields.
 *     </li>
 *     <li>
 *         Replace any instances of setting the power of a motor to 0 with
 *         a call to the stopActuation() method. This is done for the
 *         purpose of improving consistency and doesn't actually have much
 *         change on the function of the robot.
 *     </li>
 * </ul>
 * </i></p>
 *
 * @author Jordan Migneault
 */
public class CoreIntake {
    /**
     * The motor used for rolling the intake.
     */
    private final TalonSRX rollerMotor;

    /**
     * The motor used for actuating the intake.
     */
    private final TalonSRX actuationMotor;

    /**
     * Top limit switch - detect when actuation should stop.
     */
    private DigitalInput topLim;

    /**
     * Bottom limit switch - detect when actuation should stop.
     */
    private DigitalInput botLim;

    /**
     * Create a new intake with actuation limit switches.
     *
     * @param rollers   the roller motor.
     * @param actuation the actuation motor.
     * @param top       the top limit switch.
     * @param bot       the bottom limit switch.
     */
    public CoreIntake(TalonSRX rollers,
                      TalonSRX actuation,
                      DigitalInput top,
                      DigitalInput bot) {
        this.rollerMotor = rollers;
        this.actuationMotor = actuation;
        this.topLim = top;
        this.botLim = bot;
    }

    /**
     * Create a new intake without actuation limit switches.
     *
     * @param rollers   the roller motor.
     * @param actuation the actuation motor.
     */
    public CoreIntake(TalonSRX rollers, TalonSRX actuation) {
        this.rollerMotor = rollers;
        this.actuationMotor = actuation;
    }

    /**
     * Get the roller motor.
     *
     * @return the roller motor.
     */
    public TalonSRX getRollerMotor() {
        return rollerMotor;
    }

    /**
     * Get the actuation motor.
     *
     * @return the actuation motor.
     */
    public TalonSRX getActuationMotor() {
        return actuationMotor;
    }

    /**
     * Get the top limit switch.
     *
     * @return the top limit switch.
     */
    public DigitalInput getTopLim() {
        return topLim;
    }

    /**
     * Get the bottom limit switch.
     *
     * @return the bottom limit switch.
     */
    public DigitalInput getBotLim() {
        return botLim;
    }

    /**
     * Start the intake.
     *
     * @param speed how fast the motor controller should go. This is a value
     *              between -100 and 100 and represents a percentage of the
     *              controller's maximum speed.
     */
    public void startIntake(double speed) {
        double convSpeed = speed / 100;
        rollerMotor.set(PercentOutput, convSpeed);
    }

    /**
     * Stop the intake.
     */
    public void stopIntake() {
        rollerMotor.set(PercentOutput, 0);
    }

    /**
     * Make the intake go back up into the robot.
     *
     * <p>
     * First check if the limit switches are enabled or not.
     * If they're not enabled, set the power to +1 immediately. If they
     * are enabled, however, check to see if the upper limit switch has
     * been enabled and react accordingly.
     * </p>
     */
    public void actuateIntakeUp() // Intake goes back up into the robot
    {
        if (topLim != null && botLim != null) {
            if (!topLim.get()) {
                actuationMotor.set(PercentOutput, 1);
            } else if (topLim.get()) {
                stopActuation();
            }
        } else {
            actuationMotor.set(PercentOutput, 1);
        }
    }

    /**
     * Make the intake go down to start collecting power cells.
     *
     * <p>
     * First check if the limit switches are enabled or not.
     * If they're not enabled, set the power to -1 immediately. If they
     * are enabled, however, check to see if the bottom limit switch has
     * been enabled and react accordingly.
     * </p>
     */
    public void intakeActuateDown() {
        if (topLim != null && botLim != null) {
            if (botLim.get()) {
                actuationMotor.set(PercentOutput, -1);
            } else if (!botLim.get()) {
                stopActuation();
            }
        } else {
            actuationMotor.set(PercentOutput, -1);
        }
    }

    /**
     * Stop the intake's actuation.
     *
     * <p>
     * "Emergency stop."
     * </p>
     */
    public void stopActuation() {
        actuationMotor.set(PercentOutput, 0);
    }
}

