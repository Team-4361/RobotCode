package frc.team4361.config.season2021;

import frc.team4361.config.Common;

import java.util.HashMap;

/**
 * Main system configuration for the 2021 season.
 *
 * <p>
 * ALL OF THESE NEED TO BE DOUBLE AND TRIPLE CHECKED. I'm saying this because
 * I didn't enter all of these values at the same time. Please make sure that
 * all of the values are filled out (and filled out correctly).
 * </p>
 *
 * @author The ROXBOTIX Programming Subteam
 */
public class M2021 {
    /**
     * Has the configuration updated?
     *
     * <p>
     * This is being phased out as soon as I finish working on rlibx.
     * </p>
     */
    private static boolean hasUpdated = false;

    /**
     * A map, containing string keys and values.
     */
    public static HashMap<String, Double> map;

    /**
     * Force the configuration to update.
     */
    private static void forceUpdate() {
        map = new HashMap<>() {{
            /*
             * Our very lovely controllers!
             *
             * These are definitely wrong. I'm not entirely sure how to correct
             * these, but we'll figure it out.
             */
            put(Common.JOYSTICK_L_A.getName(), 0.0);
            put(Common.JOYSTICK_R_A.getName(), 1.0);

            /*
             * Swerve drive stuff!
             */

            /*
             * Drive motors - the motors that actually make the robot move, you
             * know... forwards.
             */
            put(Common.SWERVE_DRIVE_FR.getName(), 11.0);
            put(Common.SWERVE_DRIVE_FL.getName(), 4.0);
            put(Common.SWERVE_DRIVE_BR.getName(), 9.0);
            put(Common.SWERVE_DRIVE_BL.getName(), 6.0);

            /*
             * Turn motors! Very needed for turning, which is (at least in my
             * humble opinion) pretty cool.
             */
            put(Common.SWERVE_TURN_FR.getName(), 10.0);
            put(Common.SWERVE_TURN_FL.getName(), 4.0);
            put(Common.SWERVE_TURN_BR.getName(), 8.0);
            put(Common.SWERVE_TURN_BL.getName(), 5.0);

            /*
             * Swerve drive encoders.
             *
             * THESE NEED ID'S.
             */
            put(Common.SWERVE_ENCODER_DRIVE_FR_A.getName(), 9.0);
            put(Common.SWERVE_ENCODER_DRIVE_FR_B.getName(), 10.0);
            put(Common.SWERVE_ENCODER_DRIVE_FL_A.getName(), 11.0);
            put(Common.SWERVE_ENCODER_DRIVE_FL_B.getName(), 12.0);
            put(Common.SWERVE_ENCODER_DRIVE_BR_A.getName(), 13.0);
            put(Common.SWERVE_ENCODER_DRIVE_BR_B.getName(), 14.0);
            put(Common.SWERVE_ENCODER_DRIVE_BL_A.getName(), 15.0);
            put(Common.SWERVE_ENCODER_DRIVE_BL_B.getName(), 16.0);

            /*
             * Swerve turn encoders.
             *
             * THESE NEED ID'S.
             */
            put(Common.SWERVE_ENCODER_TURN_FR_A.getName(), 17.0);
            put(Common.SWERVE_ENCODER_TURN_FR_B.getName(), 18.0);
            put(Common.SWERVE_ENCODER_TURN_FL_A.getName(), 19.0);
            put(Common.SWERVE_ENCODER_TURN_FL_B.getName(), 20.0);
            put(Common.SWERVE_ENCODER_TURN_BR_A.getName(), 21.0);
            put(Common.SWERVE_ENCODER_TURN_BR_B.getName(), 22.0);
            put(Common.SWERVE_ENCODER_TURN_BL_A.getName(), 23.0);
            put(Common.SWERVE_ENCODER_TURN_BL_B.getName(), 24.0);

            /*
             * Counts per rotation of swerve encoders.
             *
             * I don't know why I put all four of these, but who cares?
             */
            put(Common.SWERVE_CPR_A.getName(), 25.0);
            put(Common.SWERVE_CPR_B.getName(), 26.0);
            put(Common.SWERVE_CPR_C.getName(), 27.0);
            put(Common.SWERVE_CPR_D.getName(), 28.0);

            /*
             * The diameter of all of the swerve wheels.
             *
             * This is used in positional tracking.
             */
            put(Common.SWERVE_DIAMETER_FR.getName(), 29.0);
            put(Common.SWERVE_DIAMETER_FL.getName(), 30.0);
            put(Common.SWERVE_DIAMETER_BR.getName(), 31.0);
            put(Common.SWERVE_DIAMETER_BL.getName(), 32.0);

            /*
             * I forget what these do, but I don't want to remove them in case
             * I break something by doing so.
             */
            put(Common.SWERVE_L_A.getName(), 33.0);
            put(Common.SWERVE_W_A.getName(), 34.0);

            /*
             * Intake stuff!
             */
            put(Common.INTAKE_ROLLER_MOTOR.getName(), 12.0);
            put(Common.INTAKE_ACTUATION_MOTOR.getName(), 1.0);
            put(Common.INTAKE_TOP_LIMIT.getName(), 0.0);    // DO THIS
            put(Common.INTAKE_BOTTOM_LIMIT.getName(), 0.0); // DO THIS

            /*
             * Shooter stuff!
             */
            put(Common.SHOOTER_MOTOR_FLYWHEEL.getName(), 0.0);
            put(Common.SHOOTER_MOTOR_ACTUATOR.getName(), 0.0);
            put(Common.SHOOTER_ENCODER_FLYWHEEL_A.getName(), 0.0);
            put(Common.SHOOTER_ENCODER_FLYWHEEL_B.getName(), 0.0);
            put(Common.SHOOTER_ENCODER_ACTUATOR_A.getName(), 0.0);
            put(Common.SHOOTER_ENCODER_ACTUATOR_B.getName(), 0.0);
            put(Common.SHOOTER_TPR_FLYWHEEL.getName(), 0.0);
            put(Common.SHOOTER_TPR_ACTUATOR.getName(), 0.0);
            put(Common.SHOOTER_DIAMETER_FLYWHEEL.getName(), 0.0);
        }};
    }

    /**
     * Get a key based on a Common key.
     *
     * @param key the key to get.
     * @return the value assigned to that key.
     */
    public static double get(Common key) {
        return get(key.getName());
    }

    /**
     * Get a key based on a String key.
     *
     * @param key the key to get.
     * @return the value assigned to that key.
     */
    public static double get(String key) {
        if (!hasUpdated) {
            forceUpdate();
            hasUpdated = true;
        }

        if (map.containsKey(key)) {
            return map.get(key);
        }

        return -1;
    }
}
