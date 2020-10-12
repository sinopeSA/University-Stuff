package ablebaker_prop;
import java.util.Scanner;
import java.util.Random;

public class AbleBaker_prop {
    public static void main(String[] args) {
      Scanner input= new Scanner(System.in);

      // ARRIVAL TIME:
      int[] IATime= {0,1,2,3,4};
      double[] Probability ={0,0.25,0.40,0.20,0.15};
      double[] cumprob= new double [Probability.length];
      double[] probRange= new double[cumprob.length];
      cumprob[0]=0; probRange[0]=0;
      for(int i=1; i<Probability.length;i++)
      {
          cumprob[i]=cumprob[i-1]+Probability[i];
          probRange[i]=100*cumprob[i]; 
      }
      
      // Serive time for Able
      int[] AbleServiceTime ={0,2,3,4,5};
      double[] AbleProbability= {0,0.30,0.28,0.25,0.17};
      double[] AbleCumProb = new double[AbleProbability.length];
      double[] AbleProbrange= new double[AbleCumProb.length];
      AbleCumProb[0]=0; AbleProbrange[0]=0;
      for(int i=1; i<AbleProbability.length;i++)
      {
          AbleCumProb[i]=AbleCumProb[i-1]+AbleProbability[i];
          AbleProbrange[i]=100*AbleCumProb[i];
      }
      
        //Service Time for Baker 
      int[] BakerServiceTime={0,3,4,5,6};
      double[] BakerProbability={0,0.35,0.25,0.20,0.20};
      double[] BakerCumProb= new double[BakerProbability.length];
      double[] BakerProbrange = new double[BakerCumProb.length];
      BakerCumProb[0]=0; BakerProbrange[0]=0;
      for(int i=1; i<Probability.length;i++)
      {
          BakerCumProb[i]=BakerCumProb[i-1]+ BakerProbability[i];
          BakerProbrange[i]=100*BakerCumProb[i];
      }
      
     // declare: 
       int[] ASS = new int[1000];
       int[] ASE = new int[1000];
       int[] ST = new int[1000];
       int[] BSS = new int[1000];
       int[] BSE = new int[1000];
       int[] IAT = new int[1000];
       int[] AT  = new int[1000];
       int[] WAIT = new int[1000];
       int[] TIS = new int[1000];
       int idxablefree=0, idxbakerfree=0,sumwait=0,sumtis=0;
       AT[0]=0; BSE[0]=0;ASE[0]=0;
             
        System.out.print("Enter the number of  Requests to be served:");
        int NReq= input.nextInt();
        Random random= new Random();
        System.out.println("Reqno \tRDNum \tIAT \tRDnum \tST \tAT \tSSA \tSEA \tSSB \tSEB \tWait \tTIS");
        
        for(int i=1;i<=NReq;i++)
        {
            int RDArrival=random.nextInt(100)+1; // generate the random number to set the arrival time
            
            for(int j=1;j<=Probability.length;j++)
                if((RDArrival>probRange[j-1]) && (RDArrival<=probRange[j])) // compare and set the arrival time
                        IAT[i]=IATime[j];
                
            AT[i]=AT[i-1]+IAT[i];
          
          int RDService =random.nextInt(100)+1;
          
          // Decide who is free
          boolean ableIsFree = ASE[idxablefree]<=AT[i];
          boolean bakerIsFree = BSE[idxbakerfree]<=AT[i];
          
          if(ableIsFree)
          {
              for(int j=1;j<AbleProbability.length;j++)
                        if((RDService > AbleProbrange[j-1])&& (RDService<=AbleProbrange[j])) //compare and decide the service time
                                ST[i] = AbleServiceTime[j];
              
                      ASS[i]= AT[i];
                      ASE[i]=ASS[i]+ST[i];
                      idxablefree=i;
                      WAIT[i]=ASS[i]-AT[i];
          }
          
          else if(bakerIsFree)
          {
             for(int j=1;j<BakerProbability.length;j++)
                     if((RDService>BakerProbrange[j-1])&&(RDService<=BakerProbrange[j])) // compare and decide and set the service time.
                             ST[i] = BakerServiceTime[j];
          
                BSS[i]=AT[i];
                BSE[i]=BSS[i]+ST[i];
                idxbakerfree =i;
                WAIT[i]=BSS[i]-AT[i];
          }
          
          
         else
          {
              if(ASE[idxablefree]<=BSE[idxbakerfree])
              {
                   for(int j=1;j<AbleProbability.length;j++)
                        if((RDService > AbleProbrange[j-1])&& (RDService<=AbleProbrange[j])) 
                                ST[i] = AbleServiceTime[j];
                   
                  ASS[i]=ASE[idxablefree];
                  ASE[i]=ASE[idxablefree]+ST[i];
                  idxablefree=i;
                  WAIT[i]=ASS[i]-AT[i];
              }
              else 
              {
                  for(int j=1;j<BakerProbability.length;j++)
                     if((RDService>BakerProbrange[j-1])&&(RDService<=BakerProbrange[j]))
                             ST[i] = BakerServiceTime[j];
                  BSS[i]=BSE[idxbakerfree];
                  BSE[i]=BSE[idxbakerfree]+ST[i];
                  idxbakerfree=i;
                  WAIT[i]=BSS[i]-AT[i];
              }
          }
          
          TIS[i]=WAIT[i]+ST[i];
          sumwait+=WAIT[i];
          sumtis+=TIS[i];
          System.out.printf("%d \t%d \t%d \t%d \t%d \t%d \t%d \t%d \t%d \t%d \t%d \t%d\n"
                  ,i,RDArrival,IAT[i],RDService,ST[i],AT[i],ASS[i],ASE[i],BSS[i],BSE[i],WAIT[i],TIS[i]);
        }
        System.out.println("\nAverage waiting time of a customer:"+((double)sumwait/(double)NReq));
        System.out.printf("Total time in the system  %d\n",sumtis);
    }
}