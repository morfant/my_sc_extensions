Noises {
	*ar {|type = 0, mul = 0.5|
		var out;
		var nameArr = [\white, \brown, \pink, \gray, \clip];
		out = switch(type,
			0, {WhiteNoise.ar(mul)},
			nameArr[0], {WhiteNoise.ar(mul)},
			1, {BrownNoise.ar(mul)},
			nameArr[1], {BrownNoise.ar(mul)},
			2, {PinkNoise.ar(mul)},
			nameArr[2], {PinkNoise.ar(mul)},
			3, {GrayNoise.ar(mul)},
			nameArr[3], {GrayNoise.ar(mul)},
			4, {ClipNoise.ar(mul)},
			nameArr[4], {ClipNoise.ar(mul)},
		);
		^out;
	}
}

// Test
// {Noises.ar(1, 0.1)}.play;
// {Noises.ar(\pink, 0.1)}.play;
// {LPF.ar(Noises.ar(\pink, 0.2), 300)}.play