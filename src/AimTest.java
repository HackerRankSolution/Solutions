import java.util.Scanner;

public class AimTest {

	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String[] alice = scanner.nextLine().split(" ");
		String[] bob = scanner.nextLine().split(" ");
		int alicePoint = 0;
		int bobPoints = 0;
		
		for(int i=0;i<alice.length;i++)
		{
			int alicePnt = Integer.parseInt(alice[i]);
			int bobPnt = Integer.parseInt(bob[i]);
			if(alicePnt > bobPnt)
			{
				alicePoint++;
			}
			else if(alicePnt < bobPnt)
			{
				bobPoints++;
			}
			
		}
		System.out.println(alicePoint+" "+bobPoints);
		
		
	}
	
	
	
	
}
