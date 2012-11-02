package textProcessing;

import java.util.Collection;

import pipe.Pipe;

public class SourcePipe extends Pipe {

	public SourcePipe(Collection t) {
		super(t);
	}

	@Override
	public Collection getInputFilters() {
		return null;
	}

	
	


}
