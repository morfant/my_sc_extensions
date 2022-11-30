
Flanger {
	*ar {|in, mix = 0.5, time = 0.0035, feedback = 0.4, rate = 0.55, depth = 1, dry = 0.5, wet = 0.5|
		var bypass, eff, lfo, rslt;
		bypass = in;
		in = in + LocalIn.ar(2);
		lfo = SinOsc.kr(rate).range(0.0, depth);
		eff = DelayC.ar(in, 10, lfo * time, 1);
		rslt = ((1.0 - mix) * bypass) + (mix * eff);
		LocalOut.ar(feedback*rslt);
		^rslt;
	}
}

