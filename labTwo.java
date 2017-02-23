package hardyWeinbergSim;

import java.util.Random;
import java.util.ArrayList;
import java.lang.Math.*;

public class labTwo {
//Larger Population
	
//Note: For in-depth documentation of the overall simulation visit the labOne.java class
	
	int popSize = 10000;//The only change in this sim is the population size (10,000 instead of 16)
	double p = 0.5;
	double q = 0.5;
	int A = 0;
	int B = 0;
	int AB = 0;
	ArrayList <Double> pHist = new ArrayList <Double>();
	ArrayList <Double> qHist = new ArrayList <Double>();
	ArrayList <Integer> Ahist = new ArrayList <Integer>();
	ArrayList <Integer> Bhist = new ArrayList <Integer>();
	ArrayList <Integer> ABhist = new ArrayList <Integer>();

	public static void main(String[] args) {
		labTwo sim = new labTwo();
		for (int k = 0; k < 25; k++){
			sim.runSim();	
		}
		sim.printStats();
	}


	public void runSim(){
		Random rand = new Random();
		double rng;
			for (int k = 0; k < popSize; k++){
				int a = 0;
				int b = 0;
				for (int j = 0; j < 2; j++){
					rng  = rand.nextDouble();
					if (rng <= p){
						a++;
					}
					else {
						b++;
					}
					if (j == 1){
						if (a == 2){
							A++;
						}
						else if (b == 2){
							B++;
						}
						else {
							AB++;
						}
					}
				}
				a = 0;
				b = 0;
			}
		updateGen();
	}
	
	public void updateGen(){
		Ahist.add(A);
		Bhist.add(B);
		ABhist.add(AB);
		pHist.add(p);
		qHist.add(q);
		p = Math.sqrt((double)A/popSize);
		q = 1 - p;
		A = 0;
		B = 0;
		AB = 0;
	}
	
	public void printStats(){
		for (Double num : pHist){
			System.out.println(num);
		}
		System.out.println("----------------------------");
		for (Double num : qHist){
			System.out.println(num);
		}
	}
}
