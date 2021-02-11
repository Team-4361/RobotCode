package frc.team4361.season2021.subsystems;

import org.roxbotix.elibs2.drive.Drive;
import org.roxbotix.elibs2.drive.SwerveV2;
import org.roxbotix.elibs2.robot.assem.SwerveModuleV2;
import org.roxbotix.elibs2.subsystem.Subsystem;
import org.roxbotix.elibs2.subsystem.Subsystems;
import frc.team4361.config.Common;
import frc.team4361.config.season2021.M2021;

/**
 * I'm really tired, so I'll add comments later. Maybe.
 *
 * @author Colin Robertson
 */
public class SwerveDTV2 implements Subsystem, Drive {
    public static void register() {
        Subsystems.registerSubsystem(
                Common.SUB_SWERVE_B,
                new SwerveDTV2(
                        (int) Math.round(M2021.get(Common.SWERVE_DRIVE_FR)),
                        (int) Math.round(M2021.get(Common.SWERVE_TURN_FR)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_DRIVE_FR_A)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_DRIVE_FR_B)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_TURN_FR_A)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_TURN_FR_B)),
                        M2021.get(Common.SWERVE_CPR_A),
                        M2021.get(Common.SWERVE_CPR_A),
                        M2021.get(Common.SWERVE_DIAMETER_FR),

                        (int) Math.round(M2021.get(Common.SWERVE_DRIVE_FL)),
                        (int) Math.round(M2021.get(Common.SWERVE_TURN_FL)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_DRIVE_FL_A)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_DRIVE_FL_B)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_TURN_FL_A)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_TURN_FL_B)),
                        M2021.get(Common.SWERVE_CPR_A),
                        M2021.get(Common.SWERVE_CPR_A),
                        M2021.get(Common.SWERVE_DIAMETER_FL),

                        (int) Math.round(M2021.get(Common.SWERVE_DRIVE_BR)),
                        (int) Math.round(M2021.get(Common.SWERVE_TURN_BR)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_DRIVE_BR_A)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_DRIVE_BR_B)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_TURN_BR_A)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_TURN_BR_B)),
                        M2021.get(Common.SWERVE_CPR_A),
                        M2021.get(Common.SWERVE_CPR_A),
                        M2021.get(Common.SWERVE_DIAMETER_BR),

                        (int) Math.round(M2021.get(Common.SWERVE_DRIVE_BL)),
                        (int) Math.round(M2021.get(Common.SWERVE_TURN_BL)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_DRIVE_BL_A)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_DRIVE_BL_B)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_TURN_BL_A)),
                        (int) Math.round(M2021.get(Common.SWERVE_ENCODER_TURN_BL_B)),
                        M2021.get(Common.SWERVE_CPR_A),
                        M2021.get(Common.SWERVE_CPR_A),
                        M2021.get(Common.SWERVE_DIAMETER_BL),

                        M2021.get(Common.SWERVE_L_A),
                        M2021.get(Common.SWERVE_W_A)
                )
        );
    }

    private final int fr_dm_id;
    private final int fr_tm_id;
    private final int fr_de_ca;
    private final int fr_de_cb;
    private final int fr_te_ca;
    private final int fr_te_cb;
    private final double fr_de_cpr;
    private final double fr_te_cpr;
    private final double fr_wheel_diameter;
    private final int fl_dm_id;
    private final int fl_tm_id;
    private final int fl_de_ca;
    private final int fl_de_cb;
    private final int fl_te_ca;
    private final int fl_te_cb;
    private final double fl_de_cpr;
    private final double fl_te_cpr;
    private final double fl_wheel_diameter;
    private final int br_dm_id;
    private final int br_tm_id;
    private final int br_de_ca;
    private final int br_de_cb;
    private final int br_te_ca;
    private final int br_te_cb;
    private final double br_de_cpr;
    private final double br_te_cpr;
    private final double br_wheel_diameter;
    private final int bl_dm_id;
    private final int bl_tm_id;
    private final int bl_de_ca;
    private final int bl_de_cb;
    private final int bl_te_ca;
    private final int bl_te_cb;
    private final double bl_de_cpr;
    private final double bl_te_cpr;
    private final double bl_wheel_diameter;
    private final double L;
    private final double W;

    public SwerveModuleV2 fr;
    public SwerveModuleV2 fl;
    public SwerveModuleV2 br;
    public SwerveModuleV2 bl;

    private SwerveV2 swerve;

    public SwerveDTV2(int fr_dm_id,
                      int fr_tm_id,
                      int fr_de_ca,
                      int fr_de_cb,
                      int fr_te_ca,
                      int fr_te_cb,
                      double fr_de_cpr,
                      double fr_te_cpr,
                      double fr_wheel_diameter,
                      int fl_dm_id,
                      int fl_tm_id,
                      int fl_de_ca,
                      int fl_de_cb,
                      int fl_te_ca,
                      int fl_te_cb,
                      double fl_de_cpr,
                      double fl_te_cpr,
                      double fl_wheel_diameter,
                      int br_dm_id,
                      int br_tm_id,
                      int br_de_ca,
                      int br_de_cb,
                      int br_te_ca,
                      int br_te_cb,
                      double br_de_cpr,
                      double br_te_cpr,
                      double br_wheel_diameter,
                      int bl_dm_id,
                      int bl_tm_id,
                      int bl_de_ca,
                      int bl_de_cb,
                      int bl_te_ca,
                      int bl_te_cb,
                      double bl_de_cpr,
                      double bl_te_cpr,
                      double bl_wheel_diameter,
                      double L,
                      double W) {
        this.fr_dm_id = fr_dm_id;
        this.fr_tm_id = fr_tm_id;
        this.fr_de_ca = fr_de_ca;
        this.fr_de_cb = fr_de_cb;
        this.fr_te_ca = fr_te_ca;
        this.fr_te_cb = fr_te_cb;
        this.fr_de_cpr = fr_de_cpr;
        this.fr_te_cpr = fr_te_cpr;
        this.fr_wheel_diameter = fr_wheel_diameter;
        this.fl_dm_id = fl_dm_id;
        this.fl_tm_id = fl_tm_id;
        this.fl_de_ca = fl_de_ca;
        this.fl_de_cb = fl_de_cb;
        this.fl_te_ca = fl_te_ca;
        this.fl_te_cb = fl_te_cb;
        this.fl_de_cpr = fl_de_cpr;
        this.fl_te_cpr = fl_te_cpr;
        this.fl_wheel_diameter = fl_wheel_diameter;
        this.br_dm_id = br_dm_id;
        this.br_tm_id = br_tm_id;
        this.br_de_ca = br_de_ca;
        this.br_de_cb = br_de_cb;
        this.br_te_ca = br_te_ca;
        this.br_te_cb = br_te_cb;
        this.br_de_cpr = br_de_cpr;
        this.br_te_cpr = br_te_cpr;
        this.br_wheel_diameter = br_wheel_diameter;
        this.bl_dm_id = bl_dm_id;
        this.bl_tm_id = bl_tm_id;
        this.bl_de_ca = bl_de_ca;
        this.bl_de_cb = bl_de_cb;
        this.bl_te_ca = bl_te_ca;
        this.bl_te_cb = bl_te_cb;
        this.bl_de_cpr = bl_de_cpr;
        this.bl_te_cpr = bl_te_cpr;
        this.bl_wheel_diameter = bl_wheel_diameter;
        this.L = L;
        this.W = W;
    }

    @Override
    public void init() {
        fr = new SwerveModuleV2(
                fr_dm_id,
                fr_tm_id,
                fr_de_ca,
                fr_de_cb,
                fr_te_ca,
                fr_te_cb,
                fr_de_cpr,
                fr_te_cpr,
                fr_wheel_diameter
        );
        fl = new SwerveModuleV2(
                fl_dm_id,
                fl_tm_id,
                fl_de_ca,
                fl_de_cb,
                fl_te_ca,
                fl_te_cb,
                fl_de_cpr,
                fl_te_cpr,
                fl_wheel_diameter
        );
        br = new SwerveModuleV2(
                br_dm_id,
                br_tm_id,
                br_de_ca,
                br_de_cb,
                br_te_ca,
                br_te_cb,
                br_de_cpr,
                br_te_cpr,
                br_wheel_diameter
        );
        bl = new SwerveModuleV2(
                bl_dm_id,
                bl_tm_id,
                bl_de_ca,
                bl_de_cb,
                bl_te_ca,
                bl_te_cb,
                bl_de_cpr,
                bl_te_cpr,
                bl_wheel_diameter
        );

        fr.init();
        fl.init();
        br.init();
        bl.init();

        swerve = new SwerveV2(
                fr,
                fl,
                br,
                bl,
                L,
                W
        );

        swerve.init();
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
    public SwerveV2 getSwerve() {
        return swerve;
    }
}
