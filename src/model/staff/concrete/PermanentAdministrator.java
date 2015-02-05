package model.staff.concrete;

import model.exceptions.SRSException;
import model.staff.StaffImpl;
import model.staff.interfaces.Administrator;
import model.staff.interfaces.Permanent;

/**
 * The PermanentAdministrator class represents a concrete permanent
 * administrator.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class PermanentAdministrator extends StaffImpl implements Permanent,
        Administrator {

    /**
     * Constructs a new PermanentAdministrator object.
     * 
     * @param id
     *            The id of the administrator.
     * @param name
     *            The name of the administrator.
     * @param pay
     *            The salary of the administrator.
     * @throws SRSException
     *             When the salary is negative.
     */
    public PermanentAdministrator(String id, String name, int pay)
            throws SRSException {
        super(id, name);
        this.employment = new PermanentImpl(this, pay);
        this.position = new AdministratorImpl(this);
    }

}
