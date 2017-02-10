import java.util.Scanner;
import java.io.*;

public class NumFiles {

    private int[][] one = makeMatrix("one.txt");
	private int[][] two= makeMatrix("two.txt");
	private int[][] three= makeMatrix("three.txt");
	private int[][] four= makeMatrix("four.txt");
	private int[][] five= makeMatrix("five.txt");
	private int[][] six= makeMatrix("six.txt");
	private int[][] seven= makeMatrix("seven.txt");
	private int[][] eight= makeMatrix("eight.txt");
	private int[][] nine= makeMatrix("nine.txt");
	private int[][] zero= makeMatrix("zero.txt");
	
	public NumFiles() { //WE WROTE THIS PART
		//create a matrix of all the numbers' matrices
		int[][][] numbers = new int[10][13][13];
		numbers[0]=getMatrix("zero"); numbers[1]=getMatrix("one"); 
		numbers[2]=getMatrix("two"); numbers[3]=getMatrix("three");
		numbers[4]=getMatrix("four"); numbers[5]=getMatrix("five");
		numbers[6]=getMatrix("six"); numbers[7]=getMatrix("seven");
		numbers[8]=getMatrix("eight"); numbers[9]=getMatrix("nine");	} //THIS IS THE END OF OUR CODE
	
	public int[][] getMatrix(String num){
		if (num == "one") {
			return one;
		}
		else if (num == "two"){
			return two;
		}
		else if (num == "three"){
			return three;
		}
		else if (num == "four") {
			return four;
		}
		else if (num == "five") {
			return five;
		}
		else if (num == "six") {
			return six;
		}
		else if (num == "seven"){
			return seven;
		}
		else if (num == "eight"){
			return eight;
		}
		else if (num == "nine") {
			return nine;
		}
		else if (num == "zero") {
			return zero;
		}
		else {
			int [][] k = {{-1},{-1}};
			return k;
		}
	}
	private int[][] makeMatrix(String filename) {
		int[][] matrix = new int[13][13];
		File fl = new File(filename);
		if (fl.exists()) {
			System.out.println("The file exists!");
		}
		try {
			Scanner fn = new Scanner(fl);
			int row = 0;
			while (fn.hasNext()) {
				String line = fn.nextLine();
				System.out.println(line);
				String[] numarr = line.split("\t");
				for (int i = 0; i<numarr.length;i++){
					matrix[row][i] = Integer.parseInt(numarr[i]);
				}
				row++;
			}
			fn.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file. Please make sure that you have your text file in the same folder as the NumFiles.class");
            System.exit(0);
        }
		return matrix;

	}
	
}
