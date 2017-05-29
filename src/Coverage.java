import java.util.ArrayList;
import java.util.Collection;


public class Coverage {


	ArrayList<Interval> intervals = new ArrayList<Interval>();


	
	public void addInterval( Interval in){
		

		intervals.add(in);
		
		// we converted the intervals arraylist to array ..
		Interval[] a = new Interval[0];
		a = intervals.toArray(a);
		
		
		Collection<Interval> toRemove = new ArrayList<Interval>(intervals.size());

		//check if it intersects with any intervals already in the Coverage
		for(int i=0; i< intervals.size(); ++i){

			Interval grower = intervals.get(i);

			for(int j=i+1; j < intervals.size(); ++j){


					Interval inter = intervals.get(j);

					if( grower.intersects( inter ) ){
	
							grower.grow( inter );
							toRemove.add( inter );//							intervals.remove( inter );

					}

			}

		}

		//and one more time to catch intervals that grow to overlap others
		for(int i=0; i< intervals.size(); ++i){

			Interval grower = intervals.get(i);

			for(int j=i+1; j < intervals.size(); ++j){


					Interval inter = intervals.get(j);

					if( grower.intersects( inter ) ){
	
							grower.grow( inter );
							toRemove.add( inter );//							intervals.remove( inter );

					}

			}

		}


		intervals.removeAll(toRemove);


/*

		Collections.sort(intervals, new Comparator<Interval>() {
  
		    		public int compare(Interval left, Interval right) {
				
      		  			return (int)(right.start - left.start)*1000;
    				}
				}
		);
*/
		
	}

	
	public String toString(){
		
	//	StringBuffer buf = new StringBuffer("Coverage[ " + intervals.size() + ": ");
	StringBuffer buf = new StringBuffer("Coverage[ ");

		for(Interval i: intervals){
			buf.append(" ");		
			buf.append(i.toString() );
			buf.append(" ");

		}
		buf.append("]");

		return buf.toString();

	}

	public boolean contains(Interval inter){

		for(Interval i: intervals){


			if( i.contains(inter) ) {  return true; }
		
		}

		return false;

	}
	
	public static void main(String args[]){
		
		Interval i1 = new Interval(2.0, 5.0); //intersects i3
		Interval i2 = new Interval(0.5, 1.0); //intersects i3
		Interval i3 = new Interval(0.7, 3.0); //intersects i1 and i2
	
		Coverage c = new Coverage();

		testAdd(i1, c);
		testAdd(i2, c);
		testAdd(i3, c);



		c = new Coverage();

		testAdd(new Interval(4.0, 5.0), c);
		testAdd(new Interval(0.5, 1.0), c);
		testAdd(new Interval(0.7, 3.0), c);
		testAdd(new Interval(2.5, 4.5), c);


		
	}	

	public static void testAdd(Interval i, Coverage c){

		System.out.println();
		System.out.println(c);
		System.out.println("Adding: "  + i);
		c.addInterval(i);
		System.out.println(c);
		System.out.println();


	}

}


