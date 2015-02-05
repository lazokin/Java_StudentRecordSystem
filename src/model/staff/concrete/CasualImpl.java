package model.staff.concrete;

import model.exceptions.SRSException;
import model.staff.Employment;
import model.staff.EmploymentType;
import model.staff.interfaces.Casual;
import model.staff.interfaces.Staff;

/**
 * The CasualImpl class represents a concrete casual employee.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class CasualImpl extends Employment implements Casual {

    private int hourlyRate;

    /**
     * Constructs a new casual employment object.
     * 
     * @param staff
     *            A reference to the parent staff object.
     * @throws SRSException
     *             When the pay is negative
     */
    public CasualImpl(Staff staff, int pay) throws SRSException {
        super(staff);
        super.employmentType = EmploymentType.Casual;
        this.setPay(pay);
    }

    @Override
    public int getPay() {
        return hourlyRate;
    }

    @Override
    public void setPay(int pay) throws SRSException {
        if (pay < 0) {
            throw new SRSException(
                    "-> Hourly rate cannot be negative. Pay not set.");
        }
        this.hourlyRate = pay;
    }

}
