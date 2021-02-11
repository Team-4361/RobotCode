package frc.team4361.season2021.surrogate;

import org.roxbotix.elibs2.robot.components.DigInput;
import org.roxbotix.elibs2.robot.components.TlMotor;
import frc.team4361.season2021.legacy.CoreIntake;

public class IntakeSurrogate {
    private final TlMotor rollerMotor;
    private final TlMotor actuationMotor;
    private final DigInput topLimit;
    private final DigInput bottomLimit;
    private final CoreIntake coreIntake;

    public IntakeSurrogate(TlMotor rollerMotor,
                           TlMotor actuationMotor,
                           DigInput topLimit,
                           DigInput bottomLimit) {
        this.rollerMotor = rollerMotor;
        this.actuationMotor = actuationMotor;
        this.topLimit = topLimit;
        this.bottomLimit = bottomLimit;

        coreIntake = new CoreIntake(
                rollerMotor.getTalon(),
                actuationMotor.getTalon(),
                topLimit.getDigitalInput(),
                bottomLimit.getDigitalInput()
        );
    }

    public TlMotor getRollerMotor() {
        return rollerMotor;
    }

    public TlMotor getActuationMotor() {
        return actuationMotor;
    }

    public DigInput getTopLimit() {
        return topLimit;
    }

    public DigInput getBottomLimit() {
        return bottomLimit;
    }

    public CoreIntake getIntake() {
        return coreIntake;
    }
}
