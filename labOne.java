package hardyWeinbergSim;

import java.util.Random;
import java.util.ArrayList;
import java.lang.Math.*;

public class labOne {
//The Initial Setup
	
	int popSize = 16; //Population size is set to 16
	double p = 0.5; //Dominant allele frequency (Set to 0.5 initially)
	double q = 0.5; //Recessive allele frequency (Set to 0.5 initially)
	
	//These three counters are recycled each generation to sort the offspring
	int A = 0; //Number of Homozygous Dominant offspring in a generation
	int B = 0; //Number of Homozygous Recessive offspring in a generation
	int AB = 0; //Number of Heterozygous offspring in a generation
	
	//These lists hold the history of these variables over generations
	ArrayList <Double> pHist = new ArrayList <Double>();
	ArrayList <Double> qHist = new ArrayList <Double>();
	ArrayList <Integer> Ahist = new ArrayList <Integer>();
	ArrayList <Integer> Bhist = new ArrayList <Integer>();
	ArrayList <Integer> ABhist = new ArrayList <Integer>();

	public static void main(String[] args) {
		labOne sim = new labOne();
		
		//We run the simulation for 25 generations
		for (int k = 0; k < 25; k++){
			sim.runSim();	
		}
		sim.printStats();
	}


	public void runSim(){
		
		//We set up a random number generator
		Random rand = new Random();
		double rng;
		
		//We generate the 16 offspring
			for (int k = 0; k < popSize; k++){
				
				//Individual allele counters are initialized
				int a = 0;
				int b = 0;
				
				//We randomly generate 2 alleles
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
				
				//Individual allele counters are reset
				a = 0;
				b = 0;
			}
		updateGen();
	}
	
	public void updateGen(){
		
		//We harvest and log data from this generation, renew allele frequencies,
		//and reset other variables to prepare for the next generation
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
		
		//We print statistics that we can then copy over to Excel
		for (Double num : pHist){
			System.out.println(num);
		}
		System.out.println("----------------------------");
		for (Double num : qHist){
			System.out.println(num);
		}
	}
}
