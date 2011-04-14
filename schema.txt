{
    schema,
    [
        {version, "0.1"},
        {default_field, "code"},
        {default_op, "or"},
        {n_val, 3},
        {analyzer_factory, {erlang, text_analyzers, noop_analyzer_factory}}
    ],
    [
    
    	%% Don't parse the field, treat it as a single token.
        {field, [
            {name, "id"},
            {analyzer_factory, {erlang, text_analyzers, noop_analyzer_factory}}
        ]},
        
        
        %% Don't parse the field, treat it as a single token.
        {field, [
            {name, "code"},
            {analyzer_factory, {erlang, text_analyzers, noop_analyzer_factory}}
        ]},
		{field, [
		    {name, "time"},
        	{type, string},
	        {analyzer_factory, "com.basho.search.analysis.WhitespaceAnalyzerFactory"}
		]}
    ]
}.