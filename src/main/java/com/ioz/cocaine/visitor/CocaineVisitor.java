package com.ioz.cocaine.visitor;

import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


@Component
public class CocaineVisitor extends VoidVisitorAdapter<Void>
{
	@Autowired
	@Lazy(true)
	private LineReader lineReader;
	
	@Override
	public void visit( NormalAnnotationExpr n, Void arg )
	{
		printAnnotation( n );
		super.visit( n, arg );
	}
	
	@Override
	public void visit( MarkerAnnotationExpr n, Void arg )
	{
		printAnnotation( n );
		super.visit( n, arg );
	}
	
	@Override
	public void visit( SingleMemberAnnotationExpr n, Void arg )
	{
		printAnnotation( n );
		super.visit( n, arg );
	}
	
	private void printAnnotation( AnnotationExpr n )
	{
		lineReader.printAbove( n.getNameAsString() + "\r\n" );
	}
}
