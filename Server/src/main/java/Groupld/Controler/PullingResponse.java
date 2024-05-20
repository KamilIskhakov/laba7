package Groupld.Controler;

import java.io.Serializable;
import java.util.HashMap;

public class PullingResponse implements Serializable {
    private final HashMap<String, CommandRequirement> requirements;
    private final RegistrationCode registrationCode;

    public PullingResponse(HashMap<String, CommandRequirement> requirements, RegistrationCode registrationCode) {
        this.requirements = requirements;
        this.registrationCode = registrationCode;
    }

    public PullingResponse(RegistrationCode registrationCode) {
        this(null, registrationCode);
    }

    public HashMap<String, CommandRequirement> getRequirements() {
        return requirements;
    }

    public RegistrationCode getRegistrationCode() {
        return registrationCode;
    }

    @Override
    public String toString() {
        return "PullingResponse{"
                + " requirements=" + requirements
                + '}';
    }
}
