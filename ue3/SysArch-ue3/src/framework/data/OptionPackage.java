package framework.data;

import java.util.Collection;

public class OptionPackage<T> implements IDataPackage<T> {

	protected Collection<String> options;
	
	
	public OptionPackage(Collection<String> options){
			this.options = options;
	}
	
	
	public boolean removeOption(String option){
		return options.remove(option);
	}
	public void addOption(String option){
		options.add(option);
	}
	public boolean hasOption(String option){
		return options.contains(option);
	}

}
