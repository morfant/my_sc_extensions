TwoNum {
	*add {|a, b|
		^(a + b);
	}

	*sub {|a, b|
		^(a - b);
	}

	*mul {|a, b|
		^(a * b);
	}

	div {|a, b|
		^(a / b);
	}

}


// a = TwoNum.add(10, 20);
// a.postln;