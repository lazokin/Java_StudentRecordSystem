package model.staff.concrete;

import model.staff.Position;
import model.staff.PositionType;
import model.staff.interfaces.Administrator;
import model.staff.interfaces.Staff;

/**
 * The AdministratorImpl class represents a concrete administrator.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class AdministratorImpl extends Position implements Administrator {

    /**
     * Constructs a new administrator position object.
     * 
     * @param staff
     *            A reference to the parent staff object.
     */
    public AdministratorImpl(Staff staff) {
        super(staff);
        super.positionType = PositionType.Administrator;
    }

    @Override
    public boolean hasAssignments() {
        return false;
    }

}
