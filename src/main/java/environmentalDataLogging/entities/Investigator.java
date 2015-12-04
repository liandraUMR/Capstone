package environmentalDataLogging.entities;

import environmentalDataLogging.enums.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

/**
 * The Investigator entity class is the link to the investigator table in the EnviroDB database.
 * A Investigator is the middle man between the Environmental technologies lab and the client.
 */
@Entity
public class Investigator
{
	/**
	 *The unique auto generated id for a investigator
	 */
	@Id
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
	@GeneratedValue(generator = "uuid-gen")
	private UUID id;

	/**
	 * The name of the investigator
	 */
	private String name;

	/**
	 * The investigators phone number
	 */
	private String phoneNumber;

	/**
	 * The investigators email
	 */
	private String email;

	/**
	 * The status of the investigator is either active or inactive
	 */
	private Status status;

	/**
	 * A comment/description about the investigator
	 */
	private String comment;

	/**
	 * The list of projects that the investigator has
	 */
	@OneToMany(mappedBy = "investigator")
	private Set<Project> projects;

	/**
	 * Gets projects.
	 *
	 * @return the projects
	 */
	public Set<Project> getProjects()
	{
		return projects;
	}

	/**
	 * Sets projects.
	 *
	 * @param projects the projects
	 */
	public void setProjects(Set<Project> projects)
	{
		this.projects = projects;
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

	/**
	 * Gets phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * Sets phone number.
	 *
	 * @param phoneNumber the phone number
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	public void setEmail(String email)
	{
		this.email = email;
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
}
