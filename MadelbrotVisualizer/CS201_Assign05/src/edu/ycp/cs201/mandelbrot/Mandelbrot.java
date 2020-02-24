package edu.ycp.cs201.mandelbrot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Mandelbrot {
	public static final int HEIGHT = 1200;
	public static final int WIDTH = 1200;
	
	public static final int numThreads = 4;
	
	public static final Color indigo = new Color(75,0,130);
	public static final Color nurple = new Color(205,29,140);
	public static final Color violet = new Color(148,0,211);
	public static final Color blue = new Color(0,0,255);
	public static final Color teal = new Color(0,204,204);
	public static final Color green = new Color(0,255,0);
	public static final Color lime = new Color(153,255,51);
	public static final Color yellow = new Color(255,255,0);
	public static final Color brightOrange = new Color(255,103,0);
	public static final Color orange = new Color(255,127,0);
	public static final Color bloodOrange = new Color(209,0,28);
	public static final Color red = new Color(255,0,0);
	public static final Color pink = new Color(255,8,127);
	public static final Color black = new Color(0,0,0);
	
	
	
	
	
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Please enter coordinates of region to render:");
		System.out.print("  x1: ");
		double x1 = keyboard.nextDouble();
		System.out.print("  y1: ");
		double y1 = keyboard.nextDouble();
		System.out.print("  x2: ");
		double x2 = keyboard.nextDouble();
		System.out.print("  y2: ");
		double y2 = keyboard.nextDouble();

		System.out.print("Output filename: ");
		String fileName = keyboard.next();
		
		// TODO: create the rendering, save it to a file
		
		//make threads
		Thread[] threads = new Thread[numThreads];
		
		//make task for each thread
		int[][] iterCounts = new int[HEIGHT][WIDTH];
		MandelbrotTask[] tasks = new MandelbrotTask[numThreads];
		
		//populate each thread with a task
		
		//Q2 (TopLeft)
		tasks[0] = new MandelbrotTask( x1, y1, x2, y2, 0, WIDTH/2, 0, HEIGHT/2, iterCounts);
		
		//Q1 (TopRight)
		tasks[1] = new MandelbrotTask( x1, y1, x2, y2, WIDTH/2, WIDTH, 0, HEIGHT/2, iterCounts);
		
		//Q3 (BotLeft)
		tasks[2] = new MandelbrotTask( x1, y1, x2, y2, 0, WIDTH/2, HEIGHT/2, HEIGHT, iterCounts);
		
		//Q4
		tasks[3] = new MandelbrotTask( x1, y1, x2, y2, WIDTH/2, WIDTH, HEIGHT/2, HEIGHT, iterCounts);
		
		//start each thread
		for(int i=0;i<numThreads;i++) {
			threads[i] = new Thread(tasks[i]);
			threads[i].start();
		}
		
		//wait for / join all the threads
		try {
			for(int i=0;i<numThreads;i++) {
				threads[i].join();
			}
		}
		catch(Exception error){
			System.out.println(error);
		}
		
		//output to png
		
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = bi.getGraphics();
		
		
		//comb through the array of itercounts and replace with colors
		
		for(int i=0; i<HEIGHT; i++) {
			for(int j=0; j<WIDTH; j++) {
			
				int mag = iterCounts[i][j];
				
				//if mag < 20, set color to indigo
				if(mag < 20) {
					g.setColor(indigo);
					g.fillRect(i, j, 1, 1);
				}
				else if(mag < 40) {
					g.setColor(nurple);
					g.fillRect(i, j, 1, 1);
				}
				//if mag < 40, set color to violet
				else if(mag < 60) {
					g.setColor(violet);
					g.fillRect(i, j, 1, 1);
				}
				//if mag < 80, set color to blue
				else if(mag < 80) {
					g.setColor(blue);
					g.fillRect(i, j, 1, 1);
				}
				else if(mag < 100) {
					g.setColor(teal);
					g.fillRect(i, j, 1, 1);
				}
				//if mag < 100, set color to green
				else if(mag < 150) {
					g.setColor(green);
					g.fillRect(i, j, 1, 1);
				}
				else if(mag < 200) {
					g.setColor(lime);
					g.fillRect(i, j, 1, 1);
				}
				//if mag < 200, set color to yellow
				else if(mag < 300) {
					g.setColor(yellow);
					g.fillRect(i, j, 1, 1);
				}
				else if(mag < 400) {
					g.setColor(brightOrange);
					g.fillRect(i, j, 1, 1);
				}
				//if mag < 400, set color to orange
				else if(mag < 500) {
					g.setColor(orange);
					g.fillRect(i, j, 1, 1);
				}
				else if(mag < 600) {
					g.setColor(bloodOrange);
					g.fillRect(i, j, 1, 1);
				}
				//if mag < 800, set color to red
				else if(mag < 800) {
					g.setColor(red);
					g.fillRect(i, j, 1, 1);
				}
				else if(mag < 900) {
					g.setColor(pink);
					g.fillRect(i, j, 1, 1);
				}
				//if mag is max, set color to black
				else {
					g.setColor(black);
					g.fillRect(i, j, 1, 1);
				}
				
				
			}
		}
		
		//try to save the BufferedImage as a png
		try {
			OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName));
			try {
				ImageIO.write(bi,"PNG",os);
			}
			catch(IOException error) {
				System.out.println(error);
			}
			finally {
				try {
					//try to close output stream
					os.close();
				}
				catch(IOException error) {
					System.out.println(error);
				}
			}
		}
		catch(FileNotFoundException error) {
			System.out.println(error);
			
		}		
	}
}
