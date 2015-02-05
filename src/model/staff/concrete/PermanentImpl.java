package model.staff.concrete;

import model.exceptions.SRSException;
import model.staff.Employment;
import model.staff.EmploymentType;
import model.staff.interfaces.Permanent;
import model.staff.interfaces.Staff;

/**
 * The PermanentImpl class represents a concrete permanent employee.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class PermanentImpl extends Employment implements Permanent {

    int salary;

    /**
     * Constructs a new permanent employment object.
     * 
     * @param staff
     *            A reference to the parent staff object.
     * @throws SRSException
     *             When the pay is negative.
     */
    public PermanentImpl(Staff staff, int pay) throws SRSException {
        super(staff);
        super.employmentType = EmploymentType.Permanent;
        this.setPay(pay);
    }

    @Override
    public int getPay() {
        return salary;
    }

    @Override
    public void setPay(int pay) throws SRSException {
        if (pay < 0) {
            throw new SRSException("-> Salary cannot be negative. Pay not set.");
        }
        this.salary = pay;
    }

}
