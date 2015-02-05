package model.exceptions;

/**
 * The SRSInputException is used to indicate an input error. The
 * SRSInputException will always include a message indicating the cause of the
 * error.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
@SuppressWarnings("serial")
public class SRSException extends Exception {

    /**
     * Construct a new SRSEception object
     * 
     * @param message
     *            A message indicating the cause of the error.
     */
    public SRSException(String message) {
        super(message);
    }

}
