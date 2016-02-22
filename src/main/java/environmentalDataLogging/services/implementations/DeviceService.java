package environmentalDataLogging.services.implementations;

import environmentalDataLogging.Helpers.PaginatedArrayList;
import environmentalDataLogging.entities.Device;
import environmentalDataLogging.entities.Device;
import environmentalDataLogging.models.FilterModel;
import environmentalDataLogging.models.SortModel;
import environmentalDataLogging.models.grids.GridRequestModel;
import environmentalDataLogging.models.grids.GridResultModel;
import environmentalDataLogging.models.views.DeviceModel;
import environmentalDataLogging.models.views.DeviceModel;
import environmentalDataLogging.repositories.IDeviceRepository;
import environmentalDataLogging.services.interfaces.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Device service provides all of the required methods for the device controller
 * to communicate with the database.  The object extends BaseService which has
 * all of the repositories injected, giving the service access.
 */
@Service
public class DeviceService extends CrudService<Device, DeviceModel> implements IDeviceService
{
	@Autowired
	IDeviceRepository repository;
	
	public GridResultModel<DeviceModel> getGridList(GridRequestModel gridRequestModel)
	{
		List<FilterModel> filters = gridRequestModel.getFilters();
		List<SortModel> sorts = gridRequestModel.getSorts();
		int pageSize = gridRequestModel.getPageSize();
		int currentPage = gridRequestModel.getCurrentPage();
		
		GridResultModel<DeviceModel> gridResultModel = new GridResultModel<>();
		List<DeviceModel> models = new ArrayList<>();
		
		List<Device> entities = repository.findAll().stream()
				.sorted((device1, device2) -> device1.getName().compareToIgnoreCase(device2.getName()))
				.collect(Collectors.toList());
		
		for ( Device entity : entities )
		{
			models.add(modelMapper.map(entity, DeviceModel.class));
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
