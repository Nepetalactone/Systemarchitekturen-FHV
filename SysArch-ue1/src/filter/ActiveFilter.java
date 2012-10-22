package filter;

public abstract class ActiveFilter<in,out> extends Filter<in, out> {

	
	public ActiveFilter(){
		super();
	}
	
	
	@Override
	public void push(in data){
		
	}
	
	@Override
	public out pull() {
		return null;
	}
	
	public void run(){
		
	}
}
