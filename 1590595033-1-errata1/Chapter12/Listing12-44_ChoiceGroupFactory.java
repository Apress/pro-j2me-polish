	public ChoiceGroup createChoiceGroup( String label, String[] choices ) {

		//#style exclusiveChoiceGroup
		ChoiceGroup group = new ChoiceGroup( label, Choice.EXCLUSIVE );

		for ( int i = 0; i < choices.length; i++ ) {
			String choice = choices[i];
			//#style exclusiveChoiceItem
			group.append( choice, null );
		}
		return group;
	}
