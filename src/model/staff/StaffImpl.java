package model.staff;

import model.PersonImpl;
import model.exceptions.SRSException;
import model.staff.interfaces.Staff;

public abstract class StaffImpl extends PersonImpl implements Staff {

    protected Employment employment = null;
    protected Position position = null;

    /**
     * Constructs a new staff member object.
     * 
     * @param id
     *            The id of the staff member.
     * @param name
     *            The name of the staff member.
     */
    public StaffImpl(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean hasAssignments() {
        return position.hasAssignments();
    }

    @Override
    public EmploymentType getEmploymentType() {
        return employment.getEmploymentType();
    }

    @Override
    public PositionType getPositionType() {
        return position.getPositionType();
    }

    @Override
    public void setPay(int pay) throws SRSException {
        employment.setPay(pay);
    }

    @Override
    public int getPay() {
        return employment.getPay();
    }

}
