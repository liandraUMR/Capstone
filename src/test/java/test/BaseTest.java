package test;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import environmentalDataLogging.EnvironmentalDataLoggingApplication;
import environmentalDataLogging.repositories.*;
import environmentalDataLogging.services.implementations.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Created by 631152 on 1/12/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EnvironmentalDataLoggingApplication.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class BaseTest
{

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SampleIdentifierRepository sampleIdentifierRepository;

    @Autowired
    SampleRepository sampleRepository;

    @Autowired
    MethodRepository methodRepository;

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InvestigatorRepository investigatorRepository;


    @Autowired
    UserService userService;





}
