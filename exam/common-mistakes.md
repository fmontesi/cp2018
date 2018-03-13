# Common mistakes found in previous exams

A list of common mistakes that have been found in previous exams. The following
is a list of things that you should *not* do.

- Handing in reports more than 3 pages long. Ensure that you have at most 3
pages, *all included*.

- Not investing enough time/effort in the report. Having a bad or good report
influenced the final grade in many cases.﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿

- Not following the directory structure described for the hand-in. Read it and follow it.

- ﻿Not really using concurrency. Some just ran multiple threads, each running a copy
of the same sequential algorithm. This is not the objective of the exam. The objective is to
use concurrency to optimise the execution of the algorithm.

- Changing the interfaces given in the skeleton program for the exam. You cannot
change the types of the methods or the interface given for the exam. Interfaces need
to be implemented via classes that you create.

- Not removing the `throw new UnsupportedMethodException()` statement given in the skeleton
program for the exam after a method has been implemented.

- Changing the return type or the parameter types of the methods given in the skeleton
program for the exam.

- Not taking care of race conditions on shared data structures. Some projects
used shared data structures to accumulate results during execution, but without
using proper synchronisation. As a result, some projects behaved erratically: some times
they would work, some times they would not.
