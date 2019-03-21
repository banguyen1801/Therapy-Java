package SecondProject;

import java.io.FileNotFoundException;

public interface Therapy {
	public void newSession();
	public String getLongestWords(String fileName) throws FileNotFoundException;
	public String getShortestWords(String fileName) throws FileNotFoundException;
	public void SortingAlphabetically(String fileName);
	public String questionInfo();
	public String ReadFile(String fileName);

}
