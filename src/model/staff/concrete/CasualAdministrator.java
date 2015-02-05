package model.staff.concrete;

import model.exceptions.SRSException;
import model.staff.StaffImpl;
import model.staff.interfaces.Administrator;
import model.staff.interfaces.Casual;

/**
 * The CasualAdministrator class represents a concrete casual administrator.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class CasualAdministrator extends StaffImpl implements Casual,
        Administrator {

    /**
     * Constructs a new CasualAdministrator object.
     * 
     * @param id
     *            The id of the administrator.
     * @param name
     *            The name of the administrator.
     * @param pay
     *            The hourly rate of the administrator.
     * @throws SRSException
     *             When the hourly rate is negative.
     */
    public CasualAdministrator(String id, String name, int pay)
            throws SRSException {
        super(id, name);
        this.employment = new CasualImpl(this, pay);
        this.position = new AdministratorImpl(this);
    }

}
