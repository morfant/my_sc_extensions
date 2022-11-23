// Pseudo-UGen
LPFs {
	*ar {|in, repeat = 1, cutoff = 440.0, mul = 1.0, add = 0.0|
		var out = in;
		repeat.do{out = LPF.ar(out, cutoff, mul, add)};
		^out;
	}
}


// Test
// {LPFs.ar(Noises.ar(\pink), 6, 660)}.play
