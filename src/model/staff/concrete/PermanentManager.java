package model.staff.concrete;

import model.exceptions.SRSException;
import model.staff.StaffImpl;
import model.staff.interfaces.Manager;
import model.staff.interfaces.Permanent;

/**
 * The PermanentManager class represents a concrete permanent manager.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class PermanentManager extends StaffImpl implements Permanent, Manager {

    /**
     * Constructs a new PermanentManager object.
     * 
     * @param id
     *            The id of the manager.
     * @param name
     *            The name of the manager.
     * @param pay
     *            The salary of the manager.
     * @throws SRSException
     *             When the salary is negative.
     */
    public PermanentManager(String id, String name, int pay)
            throws SRSException {
        super(id, name);
        this.employment = new PermanentImpl(this, pay);
        this.position = new ManagerImpl(this);
    }

}
