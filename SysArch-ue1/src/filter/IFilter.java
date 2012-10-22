package filter;

public interface IFilter<in,out> {

	void push(in data);
	out pull();
	
}
