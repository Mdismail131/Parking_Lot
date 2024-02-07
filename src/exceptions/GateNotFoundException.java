package exceptions;

import repositories.GateRepository;

public class GateNotFoundException extends Exception{
    public GateNotFoundException() {
        super("Gate not Found");
    }
}
