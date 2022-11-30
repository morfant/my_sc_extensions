//Schroeder Reverberator

Schroverb {
	*ar {|in, predt = 0.001, dmul = 1, dctime = 1, mix = 0.9|
		var temp, oneSampT = if (Server.local.hasBooted, {Server.local.sampleRate.reciprocal}, {1/44100}), x1, x2, x3, x4, s1, s2, outa, outb, outd, outc, rslt;
		oneSampT.postln;
		temp = DelayN.ar(in, 0.048, predt);
		temp = 0.7 * AllpassN.ar(temp, 0.5, oneSampT * 3470 * dmul, dctime);
		temp = 0.7 * AllpassN.ar(temp, 0.5, oneSampT * 1130 * dmul, dctime);
		temp = 0.7 * AllpassN.ar(temp, 0.5, oneSampT * 370 * dmul, dctime);
		x1 = 0.773 * CombL.ar(temp, 0.5, oneSampT * 1687 * dmul, dctime);
		x2 = 0.802 * CombL.ar(temp, 0.5, oneSampT * 1601 * dmul, dctime);
		x3 = 0.753 * CombL.ar(temp, 0.5, oneSampT * 2053 * dmul, dctime);
		x4 = 0.733 * CombL.ar(temp, 0.5, oneSampT * 2251 * dmul, dctime);
		s1 = x1 + x3;
		s2 = x2 + x4;
		outa = s1 + s2;
		outb = outa.neg;
		outd = s1 - s2;
		outc = outd.neg;
		rslt = [outa, outb, outc, outd].sum;
		rslt = (1.0 - mix) * in + (mix * temp);
		^rslt;
	}
}

		