package com.ioz.cocaine.scheduled;

import java.io.FileNotFoundException;

import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.ioz.cocaine.file.monitor.event.FileEvent;
import com.ioz.cocaine.file.monitor.event.FileEventListener;
import com.ioz.cocaine.file.monitor.event.FileOperation;
import com.ioz.cocaine.visitor.CocaineVisitor;

@Component
public class JavaFileListener implements FileEventListener
{
	@Autowired
	private CocaineVisitor visitor;
	
	@Autowired
	@Lazy(true)
	private LineReader lineReader;

	
	@Override
	public void onFileEvent( FileEvent fileEvent )
	{
		final String fileIdentifier = fileEvent.getFileDetails().getCanonicalPath();
		// Check to see if file is of interest
		if( fileIdentifier.endsWith( ".java" ) && fileEvent.getOperation() != FileOperation.MONITORING_FINISH && !fileIdentifier.endsWith( "package-info.java" ) )
		{
			lineReader.printAbove( fileIdentifier + " " + fileEvent.getOperation().name() + "\r\n" );
			try
			{
				CompilationUnit unit = StaticJavaParser.parse( fileEvent.getFileDetails().getFile() );
				unit.accept( visitor, null );
			}
			catch( FileNotFoundException e )
			{				
				throw new RuntimeException( e );
			}
			// Figure out the PhysicalTypeIdentifier
			/*final String id = typeLocationService.getPhysicalTypeIdentifier( fileIdentifier );
			if( id == null )
			{
				return;
			}*/
		}
	}
}
