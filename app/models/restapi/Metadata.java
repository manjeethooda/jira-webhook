/**
 * 
 */
package models.restapi;

import play.Play;


/**Interface to store response format for metadata of a route
 * @author ashutosh
 *
 */
public class Metadata {
	public long QTime = 0;
	
	public String latestVersion = Play.application().configuration().getString("application.version");
	
	public void setQTime(long QTime) {
		this.QTime = QTime; 
	}
	
}
