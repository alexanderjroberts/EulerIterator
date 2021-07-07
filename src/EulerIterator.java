import java.util.Arrays;

public class EulerIterator {
	
	enum Sign { PLUS, MINUS, NONE }
	
	public static void main(String[] args) {
			System.out.println(Euler(666));
	}
		
	public static int Euler(int index) {
		int[] eulerTape = new int[index];
		eulerTape[0] = 1;
		Sign[] signTape = getSignTape(index);
		
		for(int i = 1;i<index;i++) {
			//for each euler index, calculate it in turn
			int sum = 0;
			//consider each number before it, should it be added or subtracted from a total?
			for(int j = 0;j<i;j++) {
				switch(signTape[index-i+j]) {
					case PLUS:  sum+=eulerTape[j]; break;
					case MINUS: sum-=eulerTape[j]; break;
					case NONE:
					default:
				}
			}
			eulerTape[i] = sum;
		}
		return eulerTape[index-1];
	}
	
	private static Sign[] getSignTape(int size) {
		
		Sign[] signTape = new Sign[size];
		Arrays.fill(signTape, Sign.NONE);
		
		for(int ptr = size-1, gapIterator = 0; ptr>=0;) {
			//tape goes ++ -- ++ -- etc.
			signTape[ptr] = gapIterator%4 < 2 ? Sign.PLUS : Sign.MINUS;	
			ptr-=gapCalc(gapIterator++);
		}
		
		return signTape;
	}
	
	//interleaved integers and odd integers { 1,3,2,5,3,7,4,9...}
	private static int gapCalc(int whichGap) {
		return whichGap%2==0 ? whichGap/2+1 : whichGap+2;
	}

}
