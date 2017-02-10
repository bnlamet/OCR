//Briana Lamet
//Mark Barbera
//TA: Xioaran Wang

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;
/*She's giving you a class called NumFiles that reads each character into a matrix of integers
 * The field will be named after the number it represents
 * Each matrix is 13x13 representing the B&W pattern of a character
 * Fields are private. Access them using getMatrix
 * which takes as a parameter a String that holds the name of the number (zero,one,etc)
 * 
 * Your job is to complete the outline below.
 * Create a main class that calls object from ImgClass and FindBestMatch to determine the number*/

public class ImgClass {
/*Has a field for a NumFiles object that will contain all the matrices for each number.
 *This field's fields will be used for comparison to determine which number best matches
 *the pattern in the image file*/
	private NumFiles nums =  new NumFiles(); //We wrote this
	
/*has a field for the image matrix read in using the ReadMatrix() method given below,
* that reads in the file of doubles containing the image of the number*/
	double[][] imgMatrix = ReadMatrix(); //She wrote this
	
/*has a field that is a matrix of integers the same size as the imgMatrix. 
 * It will hold the black/white version of the image matrix.
 * It is created by the method cleanup which takes each value (double) in the imgMatrix
 *  and translates it to a 1 in the new matrix if the value is above .5
 *  to a 0 is the value is below .5 Scroll down to see Cleanup*/
	int[][] bwMatrix = cleanup(); //We wrote this. 

	//DON'T TOUCH. It comes from Yarrington. Avert your eyes.
	public double[][] ReadMatrix() {
		String filename = JOptionPane.showInputDialog("Enter The file to be read");
		File fl = new File(filename);
		double matrix[][] = new double[0][0];
		try {
			Scanner fn = new Scanner(fl);
			int row = 0;
			while (fn.hasNext()) {
				row ++;
				fn.nextLine();
			}
			fn.close();
			matrix = new double[row][];
			Scanner f2 = new Scanner(fl);
			row = 0;
			while (f2.hasNext()) {
				String line = f2.nextLine();
				System.out.println(line);
				String[] numarr = line.split(" ");
				int x = numarr.length;
				double[] arr = new double[x];
				matrix[row]= arr;
				for (int i = 0; i<numarr.length;i++){
					matrix[row][i] = Double.parseDouble(numarr[i]);
				}
				row++;
			}
			f2.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("Error opening file. Please make sure that you have your text file in the same folder as the NumFiles.class");
	        System.exit(0);
	    }
		return matrix;
	}

//Okay now you can mess with stuff. We wrote everything below here.
	public int[][] cleanup() {
		/*Creates and returns a brand new matrix of integers the same size as the img matrix of doubles
		 * Takes each value in the image matrix and translates it to a 1 in the new matrix if >.5
		 * Translates to 0 otherwise
		 * See example in lab3 pdf*/
		
		for(int i=0;i<imgMatrix.length;i++){
			for(int n=0;n<imgMatrix[i].length;n++){
				if (imgMatrix[i][n]>0.5){
					bwMatrix[i][n]=1;} //end if
				else{
					bwMatrix[i][n]=0;} //end else
		}}//ends for loops
		return bwMatrix;
	} //ends cleanup

	public String FindBestMatch() {
		/*Uses compareMatrices method to compare the imgMatrix to each of the num matrices in NumFiles object (nums)
		 * Keeps track of the scores returned from compareMatrices
		 * NumFile matrix with best score is considered the most likely character
		 * A string representing that number is returned
		 * for example "three is the best match" if imgMatrix vs NumFiles object's three field scores highest
		 * Hint: Easier if you make one field in NumFiles class that is an array of matrices*/
		double highscore = 0;
		String bestmatch = null;
		String n = null;
		for(int i=0;i<10;i++){
			if (i==0){n="zero";}
			if (i==1){n="one";}
			if (i==2){n="two";}
			if (i==3){n="three";}
			if (i==4){n="four";}
			if (i==5){n="five";}
			if (i==6){n="six";}
			if (i==7){n="seven";}
			if (i==8){n="eight";}
			if (i==9){n="nine";}
			double newscore = compareMatrices(nums.getMatrix(n));
			if (newscore>highscore){
				highscore = newscore;
				bestmatch = n;} //closes if
		} //closes for
		return (bestmatch + "is the best match");
	}
	public double compareMatrices(int[][] num) {
		/*This is the challenging method. Bye. */
		/*takes as input the image matrix and the num matrix
		 *Case 1: num matrix and image matrix are the same size
		 *Case 2: they aren't, so you have to move num matrix around*/
		int imgrows = imgMatrix.length; //how many arrays
		int imgcolumns = imgMatrix[0].length; //length of one array
		int numrows = num.length; //how many arrays
		int numcolumns = num[0].length; //length of one array
		int rowsdiff = imgrows - numrows;
		int columnsdiff = imgcolumns - numcolumns;
		double highscore=0; double score=0;
		int r=0; int c=0;
		for(int dr=0; dr<rowsdiff; dr++){
			for(int cd=0;cd<columnsdiff;cd++){
				//Beginning of the red box (You looking to rent some $1 DVDs?)
				for(r=0; r<numrows;){
					for(c=0; c<numcolumns;){ 
						if (num[r][c]==1 && imgMatrix[r+dr][c+cd]==1){
							score+=1;} 
						if (num[r][c]==1 && imgMatrix[r+dr][c+cd]==0){
							score-=.25;}
						if (num[r][c]==0 && imgMatrix[r+dr][c+cd]==0){
							score+=.25;}
						c++; // c++ Get it?
					} //close c loop
					r++;
				} //close r loop (END OF THE RED BOX)
				if (score>highscore){
					highscore = score;} //end score change
			} //ends cd loop (Thank god, I was tired of that track)
		}//ends dr loop (Trust me, I'm the Doctor. Doctor Loop.)

		return highscore; //which is the highest match score of all Red box locations
	} //close compareMatrices
} //close class