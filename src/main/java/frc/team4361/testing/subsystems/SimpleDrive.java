package frc.team4361.testing.subsystems;

import frc.team4361.config.Common;
import frc.team4361.config.testing.TankTesting;
import org.roxbotix.elibs2.drive.Drive;
import org.roxbotix.elibs2.motor.Direction;
import org.roxbotix.elibs2.motor.Motor;
import org.roxbotix.elibs2.motor.MotorConfig;
import org.roxbotix.elibs2.motor.Type;
import org.roxbotix.elibs2.subsystem.Subsystem;
import org.roxbotix.elibs2.subsystem.Subsystems;

public class SimpleDrive implements Subsystem, Drive {
    private final int frId;
    private final int flId;
    private final int brId;
    private final int blId;
    private Motor fr;
    private Motor fl;
    private Motor br;
    private Motor bl;

    public SimpleDrive(int frId,
                       int flId,
                       int brId,
                       int blId) {
        this.frId = frId;
        this.flId = flId;
        this.brId = brId;
        this.blId = blId;
    }

    public static void register() {
        Subsystems.registerSubsystem(
                Common.DRIVETRAIN_TANK_A,
                new SimpleDrive(
                        (int) Math.round(TankTesting.get(Common.DRIVE_FR)),
                        (int) Math.round(TankTesting.get(Common.DRIVE_FL)),
                        (int) Math.round(TankTesting.get(Common.DRIVE_BR)),
                        (int) Math.round(TankTesting.get(Common.DRIVE_BL))
                )
        );
    }

    public int getFrId() {
        return frId;
    }

    public int getFlId() {
        return flId;
    }

    public int getBrId() {
        return brId;
    }

    public int getBlId() {
        return blId;
    }

    public Motor getFr() {
        return fr;
    }

    public Motor getFl() {
        return fl;
    }

    public Motor getBr() {
        return br;
    }

    public Motor getBl() {
        return bl;
    }

    private MotorConfig getConfig(int id,
                                  Direction direction) {
        MotorConfig mc = new MotorConfig(
                id,
                Type.TALON
        );

        mc.direction = direction;

        return mc;
    }

    @Override
    public void init() {
        fr = new Motor(getConfig(frId, Direction.FORWARDS));
        fl = new Motor(getConfig(flId, Direction.BACKWARDS));
        br = new Motor(getConfig(brId, Direction.FORWARDS));
        bl = new Motor(getConfig(blId, Direction.BACKWARDS));

        fr.init();
        fl.init();
        br.init();
        bl.init();
    }

    @Override
    public String getName() {
        return "SimpleTankDrive";
    }

    @Override
    public void drive(double lsx, double lsy, double rsx, double rsy) {
        double left = -lsy;
        double right = -rsy;

        fr.set(right);
        fl.set(left);
        br.set(right);
        bl.set(left);
    }
}
