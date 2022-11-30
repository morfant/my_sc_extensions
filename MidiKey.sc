MidiKey {
	var <synthName, <freeMode, window, text, text2, button, isEnabled;
	var synthHolder, pressedNoteNumbers, pressedNoteNames, isOn;
	var octaveOffset = 0;

	*new { arg synthName = \default, freeMode = False;
		^super.newCopyArgs(synthName, freeMode).init;
	}

	init {
		isEnabled = False;
		synthHolder = ();
		pressedNoteNumbers = ();
		pressedNoteNames = "";

		isOn = (
			'a':False,
			'w':False,
			's':False,
			'e':False,
			'd':False,
			'f':False,
			't':False,
			'g':False,
			'y':False,
			'h':False,
			'u':False,
			'j':False,
			'k':False,
			'o':False,
			'l':False
		);

		this.gui();
	}

	gui {
		window = Window("MidiKey - " + synthName, bounds: Rect(0, 0, 300, 100)).front;
		window.view.background = Color.new255(153, 180, 102);
		window.onClose = {"MidiKey is Closed. Good bye~".postln};

		// Note number 표시
		text = StaticText(window, Rect(110, 20, 160, 60));
		text.align = \center;
		text.font = Font("Andale Mono", 20);
		text.string = "NOTE";

		// OctaveOffset 표시
		text2 = StaticText(window, Rect(260, 10, 30, 10));
		text2.align = \right;
		text2.font = Font("Andale Mono", 15);
		text2.string = "0";

		button = Button(window, Rect(20, 20, 100, 60)).states_([
			["OFF", Color.white, Color.black], // text color, bg color
			["ON", Color.black, Color.cyan],
		])
		.action_({ |but|
			if (but.value == 1, {isEnabled = True}, {isEnabled = False});
			isEnabled.value.postln;
		})
		.onClose_({

		});

		this.prepareAction();
	}


	prepareAction {
		window.view.keyDownAction = {|v, char, mod, uni, keycode, key|
			var note = 60 + (12 * octaveOffset);
			var sym = char.asSymbol;
			pressedNoteNames = "";
			// [v, char, mod, uni, keycode, key].postln;

			switch(sym,
				'a', {note = note + 0},
				'w', {note = note + 1},
				's', {note = note + 2},
				'e', {note = note + 3},
				'd', {note = note + 4},
				'f', {note = note + 5},
				't', {note = note + 6},
				'g', {note = note + 7},
				'y', {note = note + 8},
				'h', {note = note + 9},
				'u', {note = note + 10},
				'j', {note = note + 11},
				'k', {note = note + 12},
				'o', {note = note + 13},
				'l', {note = note + 14},
				'z', {
					octaveOffset = octaveOffset - 1; octaveOffset = max(octaveOffset, -5);
					if (octaveOffset == 0, {text2.string = "0"}, {
						if (octaveOffset > 0, {text2.string = "+"++ octaveOffset.asString},{text2.string = octaveOffset.asString});
					});
				},
				'x', {
					octaveOffset = octaveOffset + 1; octaveOffset = min(octaveOffset, 4);
					if (octaveOffset == 0, {text2.string = "0"}, {
						if (octaveOffset > 0, {text2.string = "+"++ octaveOffset.asString},{text2.string = octaveOffset.asString});
					});
				}
			);
			// ("down:" + sym).postln;
			// note.postln;
			if (isEnabled == True, {
				if (isOn[sym] == False, {
					var sorted;
					synthHolder[sym] = Synth(synthName, [\freq, note.midicps]);
					pressedNoteNumbers[sym] = note.asString;
					sorted = pressedNoteNumbers.values.sort({ |a, b| a < b });
					sorted.do{|i| pressedNoteNames = pressedNoteNames + i.asString;};
					text.string = pressedNoteNames.asString;
					isOn[sym] = True;
				})
			});
		};

		window.view.keyUpAction = {|v, char, mod, uni, keycode, key|
			var sym = char.asSymbol;
			pressedNoteNames = "";
			// [v, char, mod, uni, keycode, key].postln;
			// ("up:" + sym).postln;
			if (isEnabled == True, {
				if (synthHolder[sym].notNil, {
					var sorted;
					if (freeMode == True, {synthHolder[sym].free()}, {synthHolder[sym].release()});
					isOn[sym] = False;
					pressedNoteNumbers[sym] = nil;
					sorted = pressedNoteNumbers.values.sort({ |a, b| a < b });
					sorted.do{|i| pressedNoteNames = pressedNoteNames + i.asString;};
					text.string = pressedNoteNames.asString;
				});
			});
		};
	}

}
