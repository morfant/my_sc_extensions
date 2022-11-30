+ Test {
	play {
		arr.postln;
	}

	storeOn {|stream|
		stream << "Test.new()";
	}


	*doesNotUnderstand { arg selector ... args;
        (this.asString ++ " does not understand method " ++ selector).postln;

        if(UGen.findRespondingMethodFor(selector).notNil) {
            "But UGen understands this method".postln
        };
    }

	doesNotUnderstand { arg selector ... args;
        (this.class.asString ++ " does not understand method " ++ selector).postln;

        if(UGen.findRespondingMethodFor(selector).notNil) {
            "But UGen understands this method".postln
        };
    }
}