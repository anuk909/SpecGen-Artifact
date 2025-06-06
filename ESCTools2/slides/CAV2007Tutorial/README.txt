This directory contains materials for the tutorial by Gary T. Leavens
(with help from Joseph R. Kiniry and Erik Poll) given at CAV 2007.

The time available for this was 3 hours, split into two 1.5 hour parts.
In the event, the tutorial ran just about in the right length of time,
but this depended on not taking too much time for the demos.

To make the tutorial PDF files (for presentation, etc.), use the Makefile.

The main file is JMLTutorial.tex.  This is input in each of the
following 3 files:

  - JMLTutorialPresentation.tex           (what you'd use to give it)
  - JMLTutorialPresentationWithNotes.tex  (for off-line readers, or review)
  - JMLTutorialHandouts.tex               (for handouts given to attendees)

A starting point was the tutorial material in ../ETAPSTutorial/,
and the various talks and tutorial papers on JML.

See Objectives.txt for the goals of this tutorial.

The powerpoint files that are used to make the various pdf graphics
included are in new-diagrams.ppt.  They are printed individually to
the PDF files included in the talk, and this is done using a custom
page size of 6 inches (wide) by 10 inches (high), although on the
screen it looks like 10 inches wide by 6 high.

To get the handouts, use the Makefile or run pdflatex on
JMLTutorialHandouts.tex For this you have to be running pdflatex on a
TeX install that has pgf installed.  (Miktex has it, or can if you
install it, and the Makefile assumes that mpdflatex is the Miktex
version of pdflatex.)

Examples are mainly kept in the subdirectory examples/.
The directory examples_toedit/ contains copies of some files to use
when starting examples that are to be edited.  This directory's
contents should be copied into examples_worked before the start of the
show.  Edit the copies in examples_worked during the show.

$Id$
