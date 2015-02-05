package model;

/**
 * The PersonImpl class represents a concrete person.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public abstract class PersonImpl implements Person {

    protected String id;
    protected String name;

    public PersonImpl(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

}