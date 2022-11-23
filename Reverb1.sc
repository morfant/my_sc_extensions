Reverb1 {
	*ar {|in, wet = 0.3, cutoff = 3000|
		var out = in;
		6.do {out = LPF.ar(AllpassN.ar(out, 0.05, 0.05.rand, 1), cutoff)};
		^(out * wet) + (in * (1 - wet));
	}
}


// Test
// {Reverb1.ar(Decay.ar(Dust.ar(1), mul: FSinOsc.ar(440)), 0.3, 1000)}.play;

