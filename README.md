Protocol Synthesizer

This is a 10-year-old Java implementation of the PLDI 2013 paper TRANSIT: Specifying Protocols with Concolic Snippets.

What it does:

It is a program synthesis engine. Instead of manually coding complex, error-prone state machine transitions for distributed systems (like cache coherence protocols), the user provides a few input/output examples. The system uses the Microsoft Z3 SMT Solver in a Counterexample-Guided Inductive Synthesis (CEGIS) loop to automatically deduce and generate the exact logical expressions that satisfy those constraints.

Where the hard work is

If you are reviewing this code, go straight to ExpressionEnumerator.java.
Generating code from scratch usually causes an infinite state-space explosion. The heavy lifting of this project is in this file: it builds Abstract Syntax Trees (ASTs) from the bottom up and aggressively prunes mathematically equivalent branches to search the expression space efficiently and find the correct logic.
