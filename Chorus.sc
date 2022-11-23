// Pseudo-UGen
Chorus {
	*ar {|in, mix = 0.5, time = 0.01, rate = 0.46, amount = 0.2, phaseDiff = 0.5, mul = 0.5|
		var lfo1, lfo2, eff, rslt;
		lfo1 = SinOsc.kr(rate).range(0.0, 1.0*amount);
		lfo2 = SinOsc.kr(rate, phaseDiff.linlin(0.0, 1.0, 0, pi)).range(0.0, 1.0*amount);
		eff = Mix.ar([
			DelayC.ar(in, 1, time*lfo1, mul/2),
			DelayC.ar(in, 1, time*lfo2, mul/2),
		]);
		rslt = ((1.0 - mix) * in) + (mix * eff);
		^rslt;
	}
}
