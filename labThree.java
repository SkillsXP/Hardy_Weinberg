package hardyWeinbergSim;

import java.util.Random;
import java.util.ArrayList;
import java.lang.Math.*;

public class labThree {
//Genetic Drift
	
//Note: For in-depth documentation of the overall simulation visit the labOne.java class

	int popSize = 10000; //Population size is still 10,000
	double p = 0.5; //Initial p and q values are the same
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
		labThree sim = new labThree();
		
		/*
		 *  The main difference in this lab is we simulate a mysterious disease that
		 *  targets and kills a large number of homozygous dominant offspring in generation 12.
		 *  This disease then disappears, so we can see whether the Hardy-Weinberg equilibrium
		 *  prevents the p allele from recovering.
		 */
		
		for (int k = 0; k < 25; k++){
			sim.runSim();
			
			//In generation 12 we automatically kill homozygous dominant individuals
			if(k == 11){
				sim.killA();
			}
			sim.updateGen();
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
	
	public void killA(){
		
		//Four-fifths of the homozygous dominant offspring are killed in generation 12
		//A and popSize are changed accordingly
		int numDead = 4*A/5;
		A -= numDead;
		popSize -= numDead;
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
