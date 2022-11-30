BPFp {
	*ar {arg in, freqs = [100, 200, 300], rqs = [0.1, 0.1, 0.1], mul = 1.0, add = 0.0;
		var n_freqs = freqs.size, n_rqs = rqs.size, sig, n = 0;
		if (n_freqs != n_rqs, {n = min(n_freqs, n_rqs); "Last argument is ignored. n = min(n_freqs, n_rqs)".postln}, {n = n_freqs});
		sig = Mix.fill(n, {|i|
			BPF.ar(in, freqs[i], rqs[i], mul: mul, add: add);
		});
		^sig;
	}
}

// {BPFp.ar(PinkNoise.ar(1.0), [1000, 200, 3000, 400], [0.1, 0.1, 0.1])}.play;