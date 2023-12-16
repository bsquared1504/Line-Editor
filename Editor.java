import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Editor{
	LinkedList<String> list;
	int currentIndex = -1;

	public static void main(String[] args){
	}

	public Editor(){
		list = new LinkedList<String>();
	}

	public Editor(String fileName){
		list = new LinkedList<String>();
		try{
			Scanner scan = new Scanner(new File(fileName));
			while(scan.hasNext()){
				list.addLast(scan.nextLine());
				currentIndex++;
			}
		}catch(FileNotFoundException e){
			System.out.println(fileName+": No such file or directory");
		}
	}

	// return current Line
	public String getCurrentLine(boolean showLineNumber){
		String result = null;
		//System.out.println("index: "+index);
		if(currentIndex != -1){
			result = (showLineNumber?(currentIndex+1):"")+"\t"+list.get(currentIndex);
		}
		return result;
	}

	// set the current line according to lineNumber if possible
	// and return the current line;
	// otherwise, return null.
	public String getLine(int lineNumber, boolean showLineNumber){
		String result;
		int index = lineNumber-1;
		// Is index valid?
		if(0 <= index && index < list.size()){
			currentIndex = index;
			result = getCurrentLine(showLineNumber);
		}else{
			result = null;
		}
		return result;
	}

	public void setCurrentLine(int lineNumber){
		if ((lineNumber > 0) && (lineNumber <= list.size())){
			currentIndex = lineNumber - 1;
		}
	}

	public void addLast(String line){
		list.addLast(line);
		currentIndex = list.size() - 1;
	}

	// insert new word before the current word
	// set the new word to be the current word
	public void insert(String word){
		list.add(currentIndex, word);
		//Current index doesn't need to be updated
		//New word is placed at current index and everything else is shifted
	}

	public void insertAfter(String word){
		list.add(currentIndex + 1, word);
		currentIndex++;
		}
	// insert new word before the nth word (zero-based index)
	// set the new word to be the current word
	public void insert(String word, int n){
		if (n <= list.size()){
			list.add(n-1, word);
			currentIndex = n-1;
		}
	}

	// delete the current word
	// make the next word or the previous word current if they exist
	// otherwise, set currentIndex to -1
	public void delete(){
		list.remove(currentIndex);
		//if statement here sets current word to previous if next word isn't possible
		//will also set currentIndex to -1 if not possible
		if (currentIndex == list.size()){
			currentIndex--;
		}
	}

	// delete the nth word
	// if nth word is the current word,
	// make the (n-1)th word the current word
	public void delete(int n){
		list.remove(n);
		if (n == currentIndex){
			currentIndex = n-1;
		}
	}

	// delete a range of words from the nth to the mth inclusive
	// if the range extends beyond the valid range use the intersection o f the two ranges
	// make the immediate word after or before the range the current word if they are available
	// otherwise, set currentIndex to -1
	public void delete(int begin, int end){
		if (begin <= list.size()){
			if(end > list.size()){
				for(int i = begin; i <= list.size(); i++){
					list.remove(i-1);
				}
			}else{
				for(int j = begin; j <= end; j++){
					list.remove(j-1);
				}
			}
		}
	}

	// move the current word marker by an offset
	// if the offset is negative, move to the left
	// if the offset is postive, move to the right
	public void move(int offset){
		currentIndex += offset;
	}

	public int wordCount(){
		int result = 0;
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			String line = it.next();
			String[] words = line.split("\\s+");
			result += words.length;
		}
		return result;
	}

	public String toString(){
		return null;
	}

	public void save(String fileName){
		try{
			FileWriter outFile = new FileWriter(fileName, false);
			PrintWriter p = new PrintWriter(outFile);
			Iterator<String> it = list.iterator();

			while(it.hasNext()){
				String line = it.next();
				p.println(line);
			}
			p.close();
		}catch(IOException e){
			System.out.println(fileName+": No such directory exists");
		}
	}
}
