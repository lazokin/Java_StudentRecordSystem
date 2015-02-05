package model.staff.concrete;

import model.staff.Position;
import model.staff.PositionType;
import model.staff.interfaces.Manager;
import model.staff.interfaces.Staff;

/**
 * The ManagerImpl class represents a concrete manager.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class ManagerImpl extends Position implements Manager {

    /**
     * Constructs a new manager position object.
     * 
     * @param staff
     *            A reference to the parent staff object.
     */
    public ManagerImpl(Staff staff) {
        super(staff);
        super.positionType = PositionType.Manager;
    }

    @Override
    public boolean hasAssignments() {
        return false;
    }

}
