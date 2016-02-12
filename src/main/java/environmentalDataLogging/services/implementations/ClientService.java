package environmentalDataLogging.services.implementations;

import environmentalDataLogging.Helpers.PaginatedArrayList;
import environmentalDataLogging.entities.Client;
import environmentalDataLogging.models.FilterModel;
import environmentalDataLogging.models.SortModel;
import environmentalDataLogging.models.grids.GridRequestModel;
import environmentalDataLogging.models.grids.GridResultModel;
import environmentalDataLogging.models.views.ClientModel;
import environmentalDataLogging.repositories.IClientRepository;
import environmentalDataLogging.services.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Client service provides all of the required methods for the client controller
 * to communicate with the database.  The object extends BaseService which has
 * all of the repositories injected, giving the service access.
 */
@Service
public class ClientService extends CrudService<Client, ClientModel> implements IClientService
{
	@Autowired
	IClientRepository repository;

	public GridResultModel<ClientModel> getGridList(GridRequestModel gridRequestModel)
	{
		List<FilterModel> filters = gridRequestModel.getFilters();
		List<SortModel> sorts = gridRequestModel.getSorts();
		int pageSize = gridRequestModel.getPageSize();
		int currentPage = gridRequestModel.getCurrentPage();

		GridResultModel<ClientModel> gridResultModel = new GridResultModel<>();
		List<ClientModel> models = new ArrayList<>();

		List<Client> entities = repository.findAll().stream()
				.sorted((client1, client2) -> client1.getName().compareToIgnoreCase(client2.getName()))
				.collect(Collectors.toList());

		for ( Client entity : entities )
		{
			models.add(modelMapper.map(entity, ClientModel.class));
		}

		PaginatedArrayList paginatedArrayList = new PaginatedArrayList(models, pageSize);

		paginatedArrayList.gotoPage(currentPage - 1);

		gridResultModel.setCurrentPage(currentPage);
		gridResultModel.setLastPage(paginatedArrayList.getLastPageNumber());
		gridResultModel.setPageSize(pageSize);
		gridResultModel.setList(paginatedArrayList);

		return gridResultModel;
	}
}
