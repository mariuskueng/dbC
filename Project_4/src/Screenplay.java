
import java.util.Calendar;

public class Screenplay {
  private String screenplayId;
  private String title;
  private Calendar pubDate;
  private Screenwriter screenWriter;

  public Screenplay(String Id, String title, Calendar pubDate, Screenwriter screenWriter) {
    this.screenplayId = Id;
    this.title = title;
    this.pubDate = pubDate;
    this.screenWriter = screenWriter;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Calendar getPubDate() {
    return pubDate;
  }

  public void setPubDate(Calendar pubDate) {
    this.pubDate = pubDate;
  }

  public Screenwriter getScreenWriter() {
    return screenWriter;
  }

  public void setScreenWriter(Screenwriter screenWriter) {
    this.screenWriter = screenWriter;
  }

  @Override
  public boolean equals(Object obj) {

    if (!(obj instanceof Screenplay)) {
      return false;
    }
    if (obj == this) {
      return true;
    }

    Screenplay screenplayClone = (Screenplay) obj;
    boolean isEqual = this.screenplayId.compareTo(screenplayClone.screenplayId) == 0;

    return isEqual;
  }


}
