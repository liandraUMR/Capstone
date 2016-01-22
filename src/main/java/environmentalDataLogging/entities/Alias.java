package environmentalDataLogging.entities;

import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * The Alias entity class is the link to the alias table in the EnviroDB database.
 * A Alias is used to specify a unit of measure.
 */
@Entity
public class Alias
{
    /**
     * The unique auto generated id for a alias
     */
    @Id
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    private UUID id;

    /**
     * The name value of a alias
     */
    @NotNull
    private String name;

    @ManyToOne
    @NotNull
    private Unit unit;

    public Alias()
    {

    }
    public Alias(String name)
    {
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public UUID getId()
    {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(UUID id)
    {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
