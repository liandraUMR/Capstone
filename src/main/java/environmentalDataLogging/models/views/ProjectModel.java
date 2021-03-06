package environmentalDataLogging.models.views;

import environmentalDataLogging.entities.*;
import environmentalDataLogging.enums.Status;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * The ProjectModel class is a copy to the Project entity.
 * A Project contains all the information relating to a project off the Environmental Technologies lab
 */
public class ProjectModel
{

    /**
     * The unique auto generated id for a project
     */
    private UUID id;

    /**
     * The Environmental technologies generated project id for the project
     */
    private String projectId;

    /**
     * The name of the project
     */
    private String name;

    /**
     * The date the project was created
     */
    private Date startDate;

    /**
     * The date the project was completed
     */
    private Date endDate;

    /**
     * A list of clients the project has
     */
    private List<UUID> clients;

    /**
     * The status of the project specifying whether the project is active or inactive
     */
    private Status status;

    /**
     * The list of samples the project has
     */
    private Set<Sample> samples;

    /**
     * The id of the investigator that is in charge of delivering the project
     */
    private UUID investigatorId;

    /**
     * A list of users who have contributed to the project
     */
    private List<UUID> users;

    /**
     * A comment/description of the project
     */
    private String comment;

    public ProjectModel()
    {
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
     * Gets projectId.
     *
     * @return the projectId
     */
    public String getProjectId()
    {
        return projectId;
    }

    /**
     * Sets projectId.
     *
     * @param projectId the projectId
     */
    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
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

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate()
    {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate()
    {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    /**
     * Gets clients.
     *
     * @return the clients
     */
    public List<UUID> getClients()
    {
        return clients;
    }

    /**
     * Sets clients.
     *
     * @param clients the clients
     */
    public void setClients(List<UUID> clients)
    {
        this.clients = clients;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status)
    {
        this.status = status;
    }

    /**
     * Gets samples.
     *
     * @return the samples
     */
    public Set<Sample> getSamples()
    {
        return samples;
    }

    /**
     * Sets samples.
     *
     * @param samples the samples
     */
    public void setSamples(Set<Sample> samples)
    {
        this.samples = samples;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public List<UUID> getUsers()
    {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(List<UUID> users)
    {
        this.users = users;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment()
    {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment)
    {
        this.comment = comment;
    }

    /**
     * Gets investigatorId.
     *
     * @return the investigatorId
     */
    public UUID getInvestigatorId()
    {
        return investigatorId;
    }

    /**
     * Sets investigatorId.
     *
     * @param investigatorId the investigatorId
     */
    public void setInvestigatorId(UUID investigatorId)
    {
        this.investigatorId = investigatorId;
    }
}
