import java.util.Scanner;
import java.util.Arrays;

public class Tester{
	public static void main(String[] args){
		Editor ed;
		// a command-line argument is provided
		if(args.length >= 1){
			String fileName = args[0];
			ed = new Editor(fileName);
		}else{
			ed = new Editor();
		}

		int count = ed.wordCount();
		if(count != 0){
			System.out.println(count);
		}

		Scanner scan = new Scanner(System.in);

		while(true){
			String result = null;
			String command = scan.nextLine();
			String[] arguments = command.split("\\s+");
			int argCount = arguments.length;
			if(argCount == 1){
				command = arguments[0];
				if(command.equals("q")){
					return;
				}else if(command.equals("p")){
					result = ed.getCurrentLine(false);
				}else if(command.equals("n")){
					result = ed.getCurrentLine(true);
				}else if(command.equals("a")){
					String input = scan.nextLine();
					while(!input.equals(".")){
						ed.insertAfter(input);
						input = scan.nextLine();
					}
					result = "";
				}else if(command.equals("i")){
					String input = scan.nextLine();
					if(!input.equals(".")){
						ed.insert(input);
						input = scan.nextLine();
						while(!input.equals(".")){
							ed.insertAfter(input);
							input = scan.nextLine();
						}
					}
					result = "";
				}else if(command.equals("c")){
					ed.delete();
					String input = scan.nextLine();
					while(!input.equals(".")){
						ed.insertAfter(input);
						input = scan.nextLine();
					}
					result = "";
				}else if(command.equals("d")){
					ed.delete();
					result = "";
				}else if(command.equals("w")){
					String input = scan.nextLine();
					ed.save(input);
					result = "";
				}else{
					try{
						int lineNumber = Integer.parseInt(command);
						result = ed.getLine(lineNumber, false);
					}catch(Exception e){
						result = null;
					}
				}
			}
			if(result == null){
				result = "?";
			}
			System.out.println(result);
		}
	}
}
