/**
 ** IdentifierVec: A Vector of Identifier's. <p>
 **
 ** The interface to this class follows java.util.Vector, where
 ** appropriate. <p>
 **
 **
 ** This class was automatically created from the template
 ** javafe.util/_<tt>TYPE</tt>_Vec.j.<p>, i.e.
 ** $Id$
 **
 ** DO NOT EDIT!!<p>
 **/

/*
 * File: IdentifierVec.java <p>
 *
 * This file is a template for a vector of X.  It is filled in using
 * sed (e.g, see the Makefile for javafe.ast).  The following
 * replacements need to be done:<p>
 *
 * Identifier should be globally replaced with the simple name of the
 *		element type<p>
 * javafe.ast should be globally replaced with the package the resulting
 *		Vector type should live in<p>
 * javafe.ast should be globally replaced with the package the
 *		element type belongs to<p>
 *
 * The resulting type will have name IdentifierVec.<p>
 * 
 * Example: to make a vector of java.lang.Integer's that lives in
 * javafe.ast, substitute Integer, javafe.ast, and java.lang
 * respectively.<p>
 *
 * IdentifierVec's may not contain nulls.<p>
 */


package javafe.ast;


import java.util.Vector;
import javafe.util.StackVector;

import javafe.ast.Identifier;


public class  IdentifierVec {

    /***************************************************
     *                                                 *
     * Instance fields:				       *
     *                                                 *
     ***************************************************/

    private /*@ spec_public non_null*/ Identifier[/*#@nullable*/] elements;
    private /*@spec_public*/ int count;

    /*@ invariant 0 <= count 
      @        && count <= elements.length
      @        && (\forall int i; (0<=i & i<count) ==> elements[i]!=null);
      @ invariant \typeof(elements) == \type(javafe.ast.Expr[]);
      @ invariant elements.owner == this;
      @*/


    /***************************************************
     *                                                 *
     * Private constructors:			       *
     *                                                 *
     ***************************************************/

    //@ requires \nonnullelements(els);
    //@ ensures count == els.length;
    //@ pure
    private IdentifierVec(/*@non_null*/ Identifier[/*#@non_null*/] els) {
	this.count = els.length;
	this.elements = new Identifier[count];
	//@ set elements.owner = this;

	System.arraycopy(els,0, elements,0, count);
	// arraycopy's spec isn't strong enough ... so assume
	//@ assume (\forall int i; (0<=i & i<count) ==> els[i] == elements[i]);
    }

    //@ requires cnt >= 0;
    //@ ensures this.elements.length >= cnt;
    //@ pure
    private IdentifierVec(int cnt) {
	this.elements = new Identifier[(cnt == 0 ? 2 : cnt)];
	//@ set elements.owner = this;

	this.count = 0;
    }


    /***************************************************
     *                                                 *
     * Public maker methods:			       *
     *                                                 *
     ***************************************************/

    //@ ensures \fresh(\result);
    //@ pure
    public static /*@non_null*/ IdentifierVec make() { 
	return new IdentifierVec(0);
    }

    //@ requires count >= 0;
    //@ ensures \fresh(\result);
    public static /*@non_null*/ IdentifierVec make(int count) { 
	return new IdentifierVec(count);
    }

    //@ requires vec.elementType <: \type(Identifier);
    //@ requires !vec.containsNull;
    //@ ensures \fresh(\result);
    public static /*@non_null*/ IdentifierVec make(/*@non_null*/ Vector vec) {
	int sz = vec.size();
	IdentifierVec result = new IdentifierVec(sz);
	result.count = sz;
	vec.copyInto(result.elements);
	return result;
    }

    //@ requires \nonnullelements(els);
    //@ ensures \result.count == els.length;
    //@ ensures \fresh(\result);
    public static /*@non_null*/ IdentifierVec make(/*@non_null*/ Identifier[/*#@non_null*/] els) {
	return new IdentifierVec(els);
    }

    //@ requires s.vectorCount>1;
    //@ requires s.elementType <: \type(Identifier);
    //@ ensures \result.count == (\old(s.elementCount) - \old(s.currentStackBottom));
    // These are from pop() on s:
    //@ modifies s.vectorCount;
    //@ ensures s.vectorCount == \old(s.vectorCount)-1;
    //@ modifies s.elementCount, s.currentStackBottom;
    public static /*@non_null*/ IdentifierVec popFromStackVector(/*@non_null*/ StackVector s) {
	// Creates a new IdentifierVec from top stuff in StackVector
	int sz = s.size();
	IdentifierVec r = new IdentifierVec(sz);
	s.copyInto(r.elements);
	r.count = sz;
	s.pop();
	return r;
    }


    /***************************************************
     *                                                 *
     * Other methods:                                  *
     *                                                 *
     ***************************************************/

    //@ requires 0 <= index && index < count;
    //@ pure
    public final /*@non_null*/ Identifier elementAt(int index)
	  /*throws ArrayIndexOutOfBoundsException*/ {
	if (index < 0 || index >= count)
	    throw new ArrayIndexOutOfBoundsException(index);

	javafe.util.Assert.notNull(elements[index]);
	return /*@(non_null)*/elements[index];
    }

    //@ requires 0<=index && index<count;
    public final void setElementAt(/*@non_null*/ Identifier x, int index) 
	/*throws ArrayIndexOutOfBoundsException*/ {
	if (index < 0 || index >= count)
	    throw new ArrayIndexOutOfBoundsException(index);
	elements[index] = x;
    }

    //@ ensures \result==count;
    //@ pure
    public final int size() { return count; }

    public final /*@non_null*/String toString() {
	StringBuffer b = new StringBuffer();
	b.append("{IdentifierVec");
	for(int i = 0; i < count; i++) {
	    b.append(" ");
	    Object e = elements[i];
	    b.append(e == null ? "null" : e.toString());
	}
	b.append('}');
	return b.toString();
    }

  //@ pure
  public final /*@non_null*/ Identifier[/*#@non_null*/] toArray() {
      int ct = count;
      Identifier[] b = new Identifier[ ct ];
      for(int i = 0; i < ct; i++) {
	  b[i]=elements[i];
      }
      return b;
  }

  //@ pure
  public final /*@non_null*/ IdentifierVec copy() {
    IdentifierVec result = new IdentifierVec(count);
    result.count = count;
    System.arraycopy(elements,0,result.elements,0,count);
    return result;
  }

  public boolean contains(/*@non_null*/ Identifier x) {
    for(int i = 0; i < count; i++) {
      if( elements[i] == x ) return true;
    }
    return false;
  }

  //@ modifies count, elements;
  //@ ensures count == \old(count) + 1;
  public final void addElement(/*@non_null*/ Identifier x) {
    if( count == elements.length ) {
      Identifier[] newElements = new Identifier[ 2*(elements.length==0 ?
					      2 : elements.length) ];
      //@ set newElements.owner = this;

      System.arraycopy(elements, 0, newElements, 0, elements.length );
      elements = newElements;
    }
    elements[count++]=x;
  }

  //@ modifies count;
  public final boolean removeElement(/*@non_null*/ Identifier x) {
    for( int i=0; i<count; i++ ) {
      if( elements[i] == x ) {
	  int ct=count;
	for( int j=i+1; j<ct; j++ ) 
	  elements[j-1]=elements[j];
	count--;
	return true;
      }
    }
    return false;
  }

    //@ requires 0<=i && i<count;
    //@ modifies count;
    public final void removeElementAt(int i) {
	int ct=count;
        for (int j=i+1; j<ct; j++) {
            elements[j-1]=elements[j];
	}	    
        count--;
    }

    //@ requires count > 0;
    //@ modifies count;
    //@ ensures count == \old(count) - 1;
    public final /*@non_null*/ Identifier pop() {
        count--;
        Identifier result = elements[count];
	elements[count] = null;
	return /*@(non_null)*/result;
    }


    //@ modifies count;
    //@ ensures count==0;
    public final void removeAllElements() {
	count = 0;
    }


  //@ requires 0 <= index && index <= this.count;
  //@ modifies count, elements;
  //@ ensures count == \old(count) + 1;
  public final void insertElementAt(/*@non_null*/ Identifier obj, int index) {
    if( count == elements.length ) {
      Identifier[] newElements = new Identifier[ 2*(elements.length==0 ?
					      2 : elements.length) ];
      //@ set newElements.owner = this;
      System.arraycopy(elements, 0, newElements, 0, elements.length );
      elements = newElements;
    }
    //-@ loop_predicate i != count ==> elements[count] != null, i<= count; // FIXME - what are the semantics
    for(int i = count; i > index; i--) 
      elements[i] = elements[i-1];
    elements[index] = obj;
    count++;
  }

  //@ modifies count, elements;
  //@ ensures count== \old(count) + vec.count;
  public final void append(/*@non_null*/ IdentifierVec vec) {
      //-@ loop_predicate count == \old(count)+i, i <= vec.count; // FIXME - what are the semantics
    for( int i=0; i<vec.size(); i++)
      addElement( vec.elementAt(i) );
  }
}

/* Copyright 2000, 2001, Compaq Computer Corporation */
