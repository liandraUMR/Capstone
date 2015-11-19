package environmentalDataLogging.entities;

import environmentalDataLogging.enums.Status;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Project
{
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    private UUID id;

    @OneToOne
    private ProjectId projectId;
    private String name;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    private Client client;
    private Status status;

    @OneToMany
    private Set<Sample> samples;

    @ManyToMany
    private Set<User> users;
    private String comment;

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public ProjectId getProjectId()
    {
        return projectId;
    }

    public void setProjectId(ProjectId projectId)
    {
        this.projectId = projectId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public Set<Sample> getSamples()
    {
        return samples;
    }

    public void setSamples(Set<Sample> samples)
    {
        this.samples = samples;
    }

    public Set<User> getUsers()
    {
        return users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
