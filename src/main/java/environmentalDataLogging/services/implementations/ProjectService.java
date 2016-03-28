package environmentalDataLogging.services.implementations;

import environmentalDataLogging.entities.*;
import environmentalDataLogging.models.*;
import environmentalDataLogging.models.views.ProjectModel;
import environmentalDataLogging.repositories.IInvestigatorRepository;
import environmentalDataLogging.repositories.IProjectRepository;
import environmentalDataLogging.services.interfaces.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Project service provides all of the required methods for the project controller
 * to communicate with the database.  The object extends BaseService which has
 * all of the repositories injected, giving the service access.
 */
@Service
public class ProjectService extends CrudService<Project, ProjectModel> implements IProjectService
{
    @Autowired
    IProjectRepository repository;

    @Autowired
    IInvestigatorRepository investigatorRepository;

    @Override
    public void update(ProjectModel model)
    {
        Project entity = modelMapper.map(model, entityClass);

        Investigator investigator = investigatorRepository.findOne(model.getInvestigatorId());
        entity.setInvestigator(investigator);

        beforeUpdate(entity);
        repository.saveAndFlush(entity);
    }

    @Override
    public void delete(UUID id)
    {
        Project project = repository.findOne(id);
        project.setInvestigator(null);
        repository.saveAndFlush(project);
        repository.delete(id);
    }

    public List<SelectListModel> getProjectList()
    {
        List<Project> projects = repository.findAll();
        List<SelectListModel> models = new ArrayList<>();

        for (Project project : projects)
        {
            models.add(new SelectListModel(project.getName(), project.getId()));
        }

        return models;
    }

    public UUID createAndReturnUUID(ProjectModel model)
    {
        Project entity = modelMapper.map(model, entityClass);

        Investigator investigator = investigatorRepository.findOne(model.getInvestigatorId());
        entity.setInvestigator(investigator);

        beforeAdd(entity);
        entity = repository.saveAndFlush(entity);
        return entity.getId();
    }
}
