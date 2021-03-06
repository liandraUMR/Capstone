package environmentalDataLogging.services.implementations;

import environmentalDataLogging.Helpers.ComparatorHelper;
import environmentalDataLogging.Helpers.PaginatedArrayList;
import environmentalDataLogging.entities.Measurement;
import environmentalDataLogging.entities.Sample;
import environmentalDataLogging.enums.SortType;
import environmentalDataLogging.models.*;
import environmentalDataLogging.models.views.SampleModel;
import environmentalDataLogging.repositories.IProjectRepository;
import environmentalDataLogging.repositories.ISampleRepository;
import environmentalDataLogging.services.interfaces.ISampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

/**
 * Sample service provides all of the required methods for the sample controller
 * to communicate with the database.  The object extends BaseService which has
 * all of the repositories injected, giving the service access.
 */
@Service
public class SampleService extends CrudService<Sample, SampleModel> implements ISampleService
{
    /**
     * The Sample Repository.
     */
    @Autowired
    ISampleRepository repository;

    /**
     * The Project Repository.
     */
    @Autowired
    IProjectRepository projectRepository;

    public UUID createAndReturnUUID(SampleModel model)
    {
        Sample entity = modelMapper.map(model, entityClass);
        beforeAdd(entity);
        entity = repository.saveAndFlush(entity);
        return entity.getId();
    }

    @SuppressWarnings("unchecked")
    public GridResultModel getGridListByProjectId(GridRequestModel gridRequestModel, UUID id) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        int pageSize = gridRequestModel.getPageSize();
        int currentPage = gridRequestModel.getCurrentPage();

        GridResultModel<SampleModel> gridResultModel = new GridResultModel<>();

        List<SampleModel> models = new ArrayList<>();
        List<Object> entities = new ArrayList<>();

        Comparator<Sample> comparator = ComparatorHelper.setComparator(gridRequestModel.getSortColumn(), entityClass);
        List<Predicate> predicates = setPredicates(gridRequestModel.getFilters(), entityClass);

        repository.findByProjectId(id).stream().sorted(comparator).filter(t -> predicates.stream().allMatch(f -> f.test(t))).forEach(entities::add);

        if (gridRequestModel.getSortType().equals(SortType.DESCENDING))
        {
            Collections.reverse(entities);
        }

        for (Object entity : entities)
        {
            models.add(modelMapper.map(entity, modelClass));
        }

        PaginatedArrayList paginatedArrayList = new PaginatedArrayList(models, pageSize);

        paginatedArrayList.gotoPage(currentPage - 1);

        gridResultModel.setCurrentPage(currentPage);
        gridResultModel.setPageSize(pageSize);
        gridResultModel.setData(paginatedArrayList);
        gridResultModel.setTotalItems(models.size());
        gridResultModel.setFilters(gridRequestModel.getFilters());
        gridResultModel.setSortColumn(gridRequestModel.getSortColumn());
        gridResultModel.setSortType(gridRequestModel.getSortType());
        gridResultModel.setIgnoredColumns(gridRequestModel.getIgnoredColumns());

        return gridResultModel;
    }

    @SuppressWarnings("unchecked")
    public GridResultModel getGridListByDeviceId(GridRequestModel gridRequestModel, UUID id) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        int pageSize = gridRequestModel.getPageSize();
        int currentPage = gridRequestModel.getCurrentPage();

        GridResultModel<SampleModel> gridResultModel = new GridResultModel<>();

        List<SampleModel> models = new ArrayList<>();
        List<Object> entities = new ArrayList<>();

        Comparator<Sample> comparator = ComparatorHelper.setComparator(gridRequestModel.getSortColumn(), entityClass);
        List<Predicate> predicates = setPredicates(gridRequestModel.getFilters(), entityClass);

        repository.findByDeviceId(id).stream().sorted(comparator).filter(t -> predicates.stream().allMatch(f -> f.test(t))).forEach(entities::add);

        if (gridRequestModel.getSortType().equals(SortType.DESCENDING))
        {
            Collections.reverse(entities);
        }

        for (Object entity : entities)
        {
            models.add(modelMapper.map(entity, modelClass));
        }

        PaginatedArrayList paginatedArrayList = new PaginatedArrayList(models, pageSize);

        paginatedArrayList.gotoPage(currentPage - 1);

        gridResultModel.setCurrentPage(currentPage);
        gridResultModel.setPageSize(pageSize);
        gridResultModel.setData(paginatedArrayList);
        gridResultModel.setTotalItems(models.size());
        gridResultModel.setFilters(gridRequestModel.getFilters());
        gridResultModel.setSortColumn(gridRequestModel.getSortColumn());
        gridResultModel.setSortType(gridRequestModel.getSortType());
        gridResultModel.setIgnoredColumns(gridRequestModel.getIgnoredColumns());

        return gridResultModel;
    }

    public List<SampleExportModel> export(ExportRequestModel exportRequestModel) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<SampleExportModel> models = new ArrayList<>();
        List<Sample> entities = new ArrayList<>();

        List<Predicate> predicates = setPredicates(exportRequestModel.getFilters(), entityClass);

        repository.findAll().stream().filter(t -> predicates.stream().allMatch(f -> f.test(t))).forEach(entities::add);

        for (Sample sample : entities)
        {
            if (sample.getMeasurements().isEmpty())
            {
                Measurement measurement = new Measurement();

                SampleExportModel model = new SampleExportModel();

                model.setSampleLabId(sample.getLabId());
                if (sample.getDate() != null)
                {
                    model.setSampleCreationDate(formatter.format(sample.getDate()));
                }
                model.setSampleComment(sample.getComment());

                if (sample.getSampleIdentifier() != null)
                {
                    model.setReportId(sample.getSampleIdentifier().getCompanyName() +
                            sample.getSampleIdentifier().getCreationDate() +
                            sample.getSampleIdentifier().getSampleIdentity());
                }

                model.setMeasurementValue(measurement.getValue());
                model.setTemperature(measurement.getTemperature());
                if (measurement.getDate() != null)
                {
                    model.setMeasurementDate(formatter.format(measurement.getDate()));
                }
                if (measurement.getTestMethod() != null)
                {
                    model.setTestMethodName(measurement.getTestMethod().getName());
                }
                if (measurement.getUnit() != null)
                {
                    model.setUnitName(measurement.getUnit().getName());
                }

                models.add(model);
            }
            else
            {
                for (Measurement measurement : sample.getMeasurements())
                {
                    SampleExportModel model = new SampleExportModel();

                    model.setSampleLabId(sample.getLabId());
                    if (sample.getDate() != null)
                    {
                        model.setSampleCreationDate(formatter.format(sample.getDate()));
                    }
                    model.setSampleComment(sample.getComment());

                    if (sample.getSampleIdentifier() != null)
                    {
                        model.setReportId(sample.getSampleIdentifier().getCompanyName() +
                                sample.getSampleIdentifier().getCreationDate() +
                                sample.getSampleIdentifier().getSampleIdentity());
                    }

                    model.setMeasurementValue(measurement.getValue());
                    model.setTemperature(measurement.getTemperature());
                    if (measurement.getDate() != null)
                    {
                        model.setMeasurementDate(formatter.format(measurement.getDate()));
                    }
                    if (measurement.getTestMethod() != null)
                    {
                        model.setTestMethodName(measurement.getTestMethod().getName());
                    }
                    if (measurement.getUnit() != null)
                    {
                        model.setUnitName(measurement.getUnit().getName());
                    }

                    models.add(model);
                }
            }

        }


        return models;
    }

    /**
     * Assigns a list of samples to a specified project.
     *
     * @param ids the sample ids to assign
     * @param projectId the project to assign them to
     */
    public void assignToProject(List<UUID> ids, UUID projectId) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        for (UUID id : ids)
        {
            Sample sample = repository.getOne(id);
            sample.setProject(projectRepository.getOne(projectId));
            beforeUpdate(sample);
            repository.saveAndFlush(sample);
        }
    }
}
