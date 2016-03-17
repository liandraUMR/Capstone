package environmentalDataLogging.parsers;

import environmentalDataLogging.entities.Device;
import environmentalDataLogging.entities.Measurement;
import environmentalDataLogging.entities.Sample;
import environmentalDataLogging.enums.Status;
import environmentalDataLogging.repositories.IDeviceRepository;
import environmentalDataLogging.repositories.ITestMethodRepository;
import environmentalDataLogging.repositories.IUserRepository;
import environmentalDataLogging.tasks.InvalidImportException;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class ICPParser extends DeviceParser
{

    ITestMethodRepository testMethodRepository;
    IUserRepository userRepository;
    private String[] header;
    private Device device;
    Date date;
    boolean headerSet = false;
    DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ssa");
    Sample sample;

    public ICPParser(IDeviceRepository deviceRepository, ITestMethodRepository testMethodRepository,IUserRepository userRepository)
    {
        this.testMethodRepository = testMethodRepository;
        device = deviceRepository.findByName("ICP");
        this.userRepository = userRepository;
    }

    @Override
    public void setHeader(String[] header)
    {
        this.header = header;
    }
    @Override
    public Sample parse(String[] line,List<Sample> samples,String labId) throws InvalidImportException
    {
        Set<Measurement> measurements = new HashSet<>();
        if(line.length != 202)
        {
            throw new InvalidImportException("Sample error");
        }

        try {
            if(!line[0].endsWith("M"))
            {
                line[0] = line[0] + "M";
            }
            date = format.parse(line[0]);
            if(samples.size() == 0)
            {
                this.sample = new Sample(line[4],date, Status.ACTIVE,device, LocalDate.now(),userRepository
                        .findByEmail("SYSTEM").getId());

            }
            for(Sample sample: samples)
            {
                if(sample.getLabId().equalsIgnoreCase(line[4]))
                {
                    this.sample = sample;
                    measurements = sample.getMeasurements();
                    break;
                }
                else
                {
                    this.sample = new Sample(line[4],date, Status.ACTIVE,device, LocalDate.now(),userRepository
                            .findByEmail("SYSTEM").getId());
                    break;
                }
            }
            for(int i =11; line.length>i;i++)
            {
                if(!line[i].equalsIgnoreCase(""))
                {
                    try
                    {
                        Measurement measurement = new Measurement(Double.parseDouble(line[i]), testMethodRepository
                                .findByName(header[i]),sample,date);

                        measurements.add(measurement);

                    }catch (NumberFormatException e)
                    {
                         // //  catches invalid numbers
                    }

                }
            }
            sample.setMeasurements(measurements);

            return sample;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public String setLabId(String[] line)
    {
        return line[1];
    }

    /**
     * if line starts with published, ignore it
     * remove all header repeats
     *  ignore all lines we dont need
     */
    @Override
    public List<String[]> format(String content)
    {
        List<String[]> list = new ArrayList<>();
        String[] lines = content.split("\\r\\n");
        for(int i=0;lines.length>i;i++)
        {
            if(lines[i].contains("Ag3280") && headerSet == false)
            {
                setHeader(lines[i].split(",",-1));

                headerSet = true;
            }
            else if(lines[i].matches("(\\d).+"))
            {
                String[] split = lines[i].split(",",-1);
                if(split[4].startsWith("Blank") || split[4].startsWith("Calibration") || split[4].startsWith
                        ("CalibStd") || split[4].startsWith("MDL") || split[4].equalsIgnoreCase("") || split[4]
                        .startsWith("IPC"))
                {

                }
                else{
                    list.add(split);
                }

            }

        }
        return list;
    }
}
