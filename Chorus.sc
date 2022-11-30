
Chorus {
	// 클래스에 대해 적용될 함수(메소드)는 정의되는 이름앞에 *을 붙인다.
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
		// ^(꺾쇠)표시는 다른 언어에서의 ‘return’과 같은 의미. 뒤따라 나오는 구문의 결과값을
		// 함수를 부른(실행하라고 명령한) 곳으로 돌려준다.
	}
}