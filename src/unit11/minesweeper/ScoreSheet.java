package unit11.minesweeper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Saves/Reads Minesweeper scores in an XML format.
 * @author Ozaner Hansha
 */
@XmlRootElement(name = "scores")
@XmlAccessorType(XmlAccessType.FIELD)
public class ScoreSheet {
	
	@XmlElement(name = "score")
	private List<Score> scores = new ArrayList<Score>();
	
	public ScoreSheet(){}
	
	/**
	 * Saves the current state of the scoresheet in an xml file.
	 * @param location
	 */
	public void saveXML(String location) {
		try {
			File file = new File(location);
			JAXBContext context = JAXBContext.newInstance(ScoreSheet.class);
			Marshaller writer = context.createMarshaller();

			writer.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			writer.marshal(this, file);
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads and overrides the current state of the sheet with one form an xml file.
	 * @param location
	 */
	public void loadXML(String location) {
		try {
			File file = new File(location);
			JAXBContext context = JAXBContext.newInstance(ScoreSheet.class);
			Unmarshaller reader = context.createUnmarshaller();

			ScoreSheet s = (ScoreSheet)reader.unmarshal(file);
			scores = s.getScores();
		}
		catch (JAXBException e) {}
	}
	
	private List<Score> getScores() {
		return scores;
	}
	
	/**
	 * Adds a score to the list of it beats a record.
	 * @param row - rows of the board.
	 * @param col - columns of the board.
	 * @param time - time taken.
	 */
	public void addScore(int row, int col, int mine, int time) {
		if(col > row) {
			int temp = col;
			col = row;
			row = temp;
		}
		if(getScore(row,col) == -1)
			scores.add(new Score(row,col,mine,time));
		else if(getScore(row,col) > time) {
			for(Score s: scores) {
				if(s.getRow() == row && s.getCol() == col) {
					s.setTime(time);
				}
			}
		}
	}
	
	/**
	 * @param row - rows of board
	 * @param col - columns of board
	 * @return time took to finish board. -1 if no score present.
	 */
	public int getScore(int row, int col) {
		if(col > row) {
			int temp = col;
			col = row;
			row = temp;
		}
		for(Score s: scores) {
			if(s.getRow() == row && s.getCol() == col) {
				return s.getTime();
			}
		}
		return -1;
	}
	
	/**
	 * A class representing a score for a certain minesweeper config.
	 * @author Ozaner Hansha
	 */
	@XmlType
	static class Score {
		int row;
		int col;
		int mines;
		int time;
		
		public Score() {}
		
		public Score(int row, int col, int mines, int time) {
			//Makes row the biggest number (so 5x6 score = 6x5 score)
			if(col > row) {
				int temp = col;
				col = row;
				row = temp;
			}
			this.row = row;
			this.col = col;
			this.mines = mines;
			this.time = time;
		}
		
		@XmlElement
		public void setRow(int row){
			this.row = row;
		}
		
		public int getRow(){
			return row;
		}
		
		@XmlElement
		public void setCol(int col){
			this.col = col;
		}
		
		public int getCol(){
			return col;
		}
		
		@XmlElement
		public void setMines(int mines){
			this.mines = mines;
		}
		
		public int getMines(){
			return mines;
		}
		
		@XmlElement
		public void setTime(int time){
			this.time = time;
		}
		
		public int getTime(){
			return time;
		}
	}
}
