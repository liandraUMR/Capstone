package environmentalDataLogging.models;


public class ErrorDetailModel
{
    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String developerMessage;

    public ErrorDetailModel()
    {
    }

    public ErrorDetailModel(String title, int status, String detail, long timeStamp, String developerMessage)
    {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timeStamp = timeStamp;
        this.developerMessage = developerMessage;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public String getDeveloperMessage()
    {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage)
    {
        this.developerMessage = developerMessage;
    }
}
