Test {
	classvar <>aa = 10, <>bb = 20, <cc = 30;
	var <a, <b, <c, <>d;
	var arr = #[1,2,3];
	const <ddd = 101;
	classvar <seed = 0;

	*new {|aa, bb, cc|
		^super.newCopyArgs(aa, bb, cc);
	}

	*initClass {
		seed = 1000.rand;
		seed.postln;
	}

	age {
		^((a + b + c).rand);
	}

	call {
		this.age;
	}

	printOn {|stream|
		stream << "Hello " << this.class.asString << "!";
	}

	prSayHello {
		"Hello!".postln;
	}
}