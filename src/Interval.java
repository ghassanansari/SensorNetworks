
public class Interval {

	double start;
	double end;
	
	final	double TOLERANCE = 0.01;
	
	// dummy constructor 
	public Interval(){ 
		this(0.0, 0.0);
	}

	// full constructor
	public Interval(double s, double e) {
		start = s; 
		end = e;
	}
	
	
	public Interval grow(Interval inter){
		
		if(!this.intersects(inter) ){ return this;}
		
		if(inter.start <= start){
			start = inter.start;
		}
		if(end <= inter.end){
			end = inter.end;
		}
		
		
		return this;
	}

	// to check if Sensor x intersects sensor y .. EX: s1.intersects(s3); --> true Or false 
	public boolean intersects(Interval inter) {
		
		return 
				 (start <= inter.start + TOLERANCE &&  inter.start <= end + TOLERANCE) 
			||  (start <= inter.end + TOLERANCE	&&  inter.end   <= end + TOLERANCE);
	
	}
	
	// to check if sensor x contains sensor y FULLY !! 
	public boolean contains(Interval inter){

		return start <= inter.start+TOLERANCE && inter.end <= end+ TOLERANCE;


	}
	
	public String toString(){
		
		return String.format("(%.2f, %.2f)", start,  end );
	}
	

	
	
	///////////////// test ////////////////////////
	public static void main(String args[]){
		
		Interval i1 = new Interval(2.0, 5.0); //intersects i3
		Interval i2 = new Interval(0.5, 1.0); //intersects i3
		Interval i3 = new Interval(0.75, 3.0); //intersects i1 and i2
	
		testIntersects(i1, i2);
		testIntersects(i1, i3);
		testIntersects(i2, i3);
		

		System.out.println();
		System.out.println("i1:" + i1);

		System.out.println("i3:" + i3);
		i1.grow(i3);
		
		//more tests

		System.out.println("after i1.grow(i3), i1:" + i1);
		
		
		
	}

	public static void testIntersects(Interval a, Interval b){
		System.out.println(a + ".intersects " + b + " ?:" + a.intersects(b)  );
		System.out.println(b + ".intersects " + a + " ?:" + b.intersects(a)  );
		
	}

	
}
