//#FLAGS: -nowarn SpecificationException -strictExceptions

public class UndeclaredInheritedException2 {
UndeclaredInheritedException2();


public void q(Object oo) {
    Object o = null;
    NEx x = new NEx();
    //@ assert x.m(o); // WARNING - because result is undefined with o==null
    //@ assert false;  // WARNING - gets here because x.m is not declared to
                        // throw an exception
}

public void qx(Object oo) {
    Object o = null;
    Ex x = new Ex();
    //@ assert x.m(o); // WARNING - because result is undefined with o==null
    //@ assert false;  // WARNING - gets here because exceptions are ignored
			// in assertions - FIXME - should warn of these
}

public void q1(Object oo) {
    Object o = new Object();
    NEx x = new NEx();
    //@ assert x.m(o); // OK

}

public void q1x(Object oo) {
    Object o = new Object();
    Ex x = new Ex();
    //@ assert x.m(o); // OK

}

public void q3(Object oo) {
    Object o = new Object();
    NEx x = new NEx();
    //@ assert x.m(oo); // WARNING - not sure oo is non-null

}

public void q3x(Object oo) {
    Object o = new Object();
    Ex x = new Ex();
    //@ assert x.m(oo); // WARNING - not sure oo is non-null

}



public void q4(Object oo) {
    Object o = new Object();
    NEx x = new NEx();
    x.m(oo); // argument might be null, but since NEx.m is not declared
             // to throw an exception, the result is essentially undefined,
             // but with normal termination.  However, from the specification
             // we can tell that an exception might be thrown that is not
             // listed in the throws clause, so we warn about that.
} 

public void q4x(Object oo) {
    Object o = new Object();
    Ex x = new Ex();
    x.m(oo);
}  // WARNING - an exception might be thrown

//@ requires x != null;
public void rr(NEx x) {
    try {
        x.m(null); // WARNING about an undeclared exception that might be
                   // thrown here
        //@ assert false;
    } catch (Exception e) {
        //@ assert (e instanceof NullPointerException);
    }
//@ assert false; // WARNING - but not until a bug is fixed
}

//@ requires x != null;
public void rrx(Ex x) {
    try {
        x.m(null);
        //@ assert false; // does not get here
    } catch (Exception e) {
        //@ assert (e instanceof NullPointerException);
    }
//@ assert false;  // WARNING
}


static public class SNEx {
SNEx();

    //@ public normal_behavior
    //@    requires o != null;
    //@    ensures \result;
    //@ also public exceptional_behavior
    //@    requires o == null;
    //@    signals_only NullPointerException;
    //@ pure
    public boolean m(Object o) {
      if (o == null) throw new NullPointerException();
      return true;
    } // WARNING - Unexpected Exception
}

static public class NEx extends SNEx {
NEx();


    // inherits spec
    public boolean m(Object o);
}

static public class SEx {
SEx();

    //@ public normal_behavior
    //@    requires o != null;
    //@    ensures \result;
    //@ also public exceptional_behavior
    //@    requires o == null;
    //@    signals_only NullPointerException;
    //@ pure
    public boolean m(Object o) throws NullPointerException {
      if (o == null) throw new NullPointerException();
      return true;
    }

}

static public class Ex extends SEx {
Ex();
    // inherits spec
    public boolean m(Object o) throws NullPointerException;
}
}



