package view.io;

public class StaffFileEntry {

    private String id;
    private String name;
    private String pay;
    private String role;

    public StaffFileEntry() {
        super();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPay() {
        return pay;
    }

    public String getRole() {
        return role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
