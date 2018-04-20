

public class HelloWorld {
	  public static void main(String[] args) {

	        int answer = divideWithoutSymbol(12,3);
	        System.out.println(answer);
	    }
	 
	  
	  //recusrive division not using a / symbol
	  private static int divideWithoutSymbol(int a, int b) {
		  			//divide a by b
		  if( a == 0 )
          {
              return 0;
          }
          else if(a-b == 0)
          {
             return 1;
          }
          else if( a < b)
          {
             return 0; 
          }
          else
          {		  			
		  			return (divideWithoutSymbol(a-b,b))+1;
          }
	  }
}
