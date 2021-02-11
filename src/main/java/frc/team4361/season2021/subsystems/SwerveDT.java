package frc.team4361.season2021.subsystems;

import org.roxbotix.elibs2.drive.Drive;
import org.roxbotix.elibs2.drive.Swerve;
import org.roxbotix.elibs2.subsystem.Subsystem;
import org.roxbotix.elibs2.subsystem.Subsystems;
import org.roxbotix.elibs2.subsystem.subsystems.SwerveSystem;
import frc.team4361.config.Common;
import frc.team4361.config.season2021.M2021;

public class SwerveDT implements Subsystem, Drive {
    static {
        Subsystems.registerSubsystem(
                Common.SUB_SWERVE_A,
                new SwerveSystem(
                        new int[]{
                                (int) M2021.get(Common.DRIVE_FRD),
                                (int) M2021.get(Common.DRIVE_FRT),
                                (int) M2021.get(Common.ENCODER_SWERVE_FR),
                                (int) M2021.get(Common.SWERVE_CPR_A)
                        },
                        new int[]{
                                (int) M2021.get(Common.DRIVE_FLD),
                                (int) M2021.get(Common.DRIVE_FLT),
                                (int) M2021.get(Common.ENCODER_SWERVE_FL),
                                (int) M2021.get(Common.SWERVE_CPR_A)
                        },
                        new int[]{
                                (int) M2021.get(Common.DRIVE_BRD),
                                (int) M2021.get(Common.DRIVE_BRT),
                                (int) M2021.get(Common.ENCODER_SWERVE_BR),
                                (int) M2021.get(Common.SWERVE_CPR_A)
                        },
                        new int[]{
                                (int) M2021.get(Common.DRIVE_BLD),
                                (int) M2021.get(Common.DRIVE_BLT),
                                (int) M2021.get(Common.ENCODER_SWERVE_BL),
                                (int) M2021.get(Common.SWERVE_CPR_A)
                        }
                )
        );
    }

    /**
     * The internally-used swerve drivetrain.
     */
    private final Swerve swerve;

    /**
     * Front-right IDs.
     */
    private final int[] fris;

    /**
     * Front-left IDs.
     */
    private final int[] flis;

    /**
     * Back-right IDs.
     */
    private final int[] bris;

    /**
     * Back-left IDs.
     */
    private final int[] blis;

    /**
     * Initialize the drivetrain. I'd strongly suggest you read the rest of this
     * JavaDoc if you'd like to have an idea as to what's going on here.
     *
     * <p>
     * Each of the inputted arrays should be of length FOUR - meaning there
     * is FOUR different integers in the array. Those integers are as follows.
     * <ul>
     *     <li>Drive motor hardware ID.</li>
     *     <li>Turn motor hardware ID.</li>
     *     <li>Turn motor encoder hardware ID.</li>
     *     <li>Turn motor encoder's CPR (counts per rotation).</li>
     * </ul>
     * </p>
     *
     * @param fris the hardware IDs for the front-right motor. Check
     *             this JavaDoc for more information regarding this
     *             array and what it contains.
     * @param flis the hardware IDs for the front-left motor. Check
     *             this JavaDoc for more information regarding this
     *             array and what it contains.
     * @param bris the hardware IDs for the back-right motor. Check
     *             this JavaDoc for more information regarding this
     *             array and what it contains.
     * @param blis the hardware IDs for the back-left motor. Check
     *             this JavaDoc for more information regarding this
     *             array and what it contains.
     */
    public SwerveDT(int[] fris,
                    int[] flis,
                    int[] bris,
                    int[] blis) {
        this.fris = fris;
        this.flis = flis;
        this.bris = bris;
        this.blis = blis;

        swerve = new Swerve();
    }

    @Override
    public void init() {
        swerve.init(
                fris,
                flis,
                bris,
                blis
        );
    }

    @Override
    public String getName() {
        return "Swerve Subsystem";
    }

    /**
     * Drive the drivetrain.
     *
     * @param lsx LEFT STICK X
     * @param lsy LEFT STICK Y
     * @param rsx RIGHT STICK X
     * @param rsy RIGHT STICK Y
     */
    @Override
    public void drive(double lsx,
                      double lsy,
                      double rsx,
                      double rsy) {
        swerve.drive(
                lsx,
                lsy,
                rsx,
                rsy
        );
    }

    /**
     * Get the internal swerve system.
     *
     * @return the swerve system that's being used internally.
     */
    public Swerve getSwerve() {
        return swerve;
    }
}
