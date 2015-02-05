package model.staff.concrete;

import model.exceptions.SRSException;
import model.staff.StaffImpl;
import model.staff.interfaces.Casual;
import model.staff.interfaces.Manager;

/**
 * The CasualManager class represents a concrete casual manager.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class CasualManager extends StaffImpl implements Casual, Manager {

    /**
     * Constructs a new CasualManager object.
     * 
     * @param id
     *            The id of the manager.
     * @param name
     *            The name of the manager.
     * @param pay
     *            The hourly rate of the manager.
     * @throws SRSException
     *             When the hourly rate is negative.
     */
    public CasualManager(String id, String name, int pay) throws SRSException {
        super(id, name);
        this.employment = new CasualImpl(this, pay);
        this.position = new ManagerImpl(this);
    }

}
