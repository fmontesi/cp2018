# Exam Instructions

To pass the course, you have to prepare a software project and a written report
on it. Everything has to be uploaded as a single zip file containing the following:
- `report.pdf`: this is the written report.
- `code`: a directory containing all source code of the software project.

Detailed instructions on the two items are given below.

You can find a list of common mistakes that people do in the exam for this course in [common mistakes](https://github.com/fmontesi/cp2018/blob/master/exam/common-mistakes.md). Please read it after reading the following instructions.

## Hand-in

An assignment will be opened on Blackboard for handing in the project. The deadline for uploading the project on Blackboard will be: 7 May 2018 at midnight.

## Report

The report must be written in English and be at max 3 page long.

Use 1.5 line spacing and ensure that the page margins are at least of 2cm (in all directions).
For the body text, use Times new roman as family and at least 11pt as font size.

If you are using LaTeX, an easy way to obtain this is:
```
\documentclass[11pt]{article}
\usepackage[a4paper,top=2cm,bottom=2cm,left=2cm,right=2cm]{geometry}
\usepackage{times}

% ... rest of your preamble before the document ...

\begin{document}
\setlength{\baselineskip}{1.44\baselineskip} % This sets the line spacing to something that looks like 1.5 in Word

\end{document}
```

In the first page of the report, you must state
the name of the course, your name, your e-mail address, and the date (in which
you last edited the document). The report must contain the following sections:
- Methodology. Here you explain how you designed your software. Give a brief
  overview of how it works and then focus on how you handle concurrency.
  For example, did you use futures, executors, manual control over threads,
  and/or monitors? How did you use them?
- Advantages. Here you explain the nice points of your implementation, i.e.,
  the advantages that come out of your design choices.
- Limitations. Here you explain the limitations of your approach and, possibly,
  how they could be overcome. Did you sacrifice performance for achieving better
  readability of your code? Or, did you sacrifice readability for improving
  efficiency? Is there a way to improve how you coordinate concurrent computation?

Inspiration points to discuss advantages and
limitations:
- Readability
- Speed
- Scalability (scales well with the number of cores, or amount of memory)
- Reusability (the code can be reused in different contexts)
- Memory consumption
- Reliability (handling of errors/exceptions)

## Software project

The `code` subdirectory in the zip file must contain a `exam_project` subdirectory that implements the `Exam` class found in this directory (see `Exam` in the subdirectory `exam_project/src/cp`).
To be sure that you are getting the class names and method signatures right, copy the `exam_project` subdirectory from this repository and start from there. Concretely, you must implement the methods `m1`, `m2` and `m3` in class `Exam`.
The class contains documentation about what these methods are supposed to do.

I suggest that you use the [NetBeans IDE](https://netbeans.org/) to develop the project. The easiest way is to follow the steps below:
- Open the project `exam_project` that you have just copied in your filesystem.
- Edit method `main` in class `Main` to test the methods you have implemented in `Exam`. Your code in `Main.main` will be completely disregarded in the evaluation. Only the implementation of `Exam` will be evaluated, using an automated tester.

Using NetBeans is an easy way to ensure that I will be able to compile your code correctly.
If the code does not compile you may automatically fail the exam, so make sure that it does!
In particular, if you do not use NetBeans, you must ensure that your project can be correctly
compiled by running the command `ant jar`, and that this results in a file called `exam_project.jar` inside of the subdirectory `dist`.
If you use NetBeans and the provided `exam_project` template, all of this is automatically ensured.


*IMPORTANT:*
- The directory structure of your project *must* be the same as the `exam_project` subdirectory I provide. Do not make your own project structure.
- You can find a directory that you can use to test your project in the directory `data_example`, located in the same directory as this readme file. The directory I will use in the evaluation will follow the same format, but it will be different.
- You can add your own classes, but you cannot change the types of what the the
methods take as parameters or return. You cannot change the provided
interfaces in any way. If you do, you risk failing the exam automatically.
- When you start implementing a method, remove the `throw new UnsupportedOperationException();` line from it first. If you do not manage to
implement a method, then keep that line instead so that I know you chose not
to give an implementation for that particular method.
- You do not need to implement all methods to pass, but you need at least a good
implementation of one of them. If you implement only one method and it does
not work well, then you risk failing.


# Frequently Asked Questions and Comments

- What version of NetBeans should we use? Use at least version 8.

- The project will be tested using Java 8.

- Including external libraries is forbidden.
You can only use the Java standard library.

- Be careful with thread termination: when I measure the time it takes your method calls to terminate, having threads that have not terminated may slow down the measurement.

- How do I traverse directories?
There are different ways to traverse directories in Java. See: [http://www.adam-bien.com/roller/abien/entry/listing_directory_contents_with_jdk](http://www.adam-bien.com/roller/abien/entry/listing_directory_contents_with_jdk) and [https://docs.oracle.com/javase/tutorial/essential/io/walk.html](https://docs.oracle.com/javase/tutorial/essential/io/walk.html). There is no "best" way for the project, since maybe one way will play nicer than the others with how you intend to program concurrency. You can also see the (simple!) example in `lectures`.

- What about the storage?
I will use an SSD for testing the projects.

- What encoding will the files be in?
UTF-8. I will not use any weird characters that require thinking of more than a Java `char`. I do not recommend using byte representations of strings.

- This is useful if you want to test how your project scales with respect to the number of cores: How do I de-activate CPU cores in Linux?
See [http://www.cyberciti.biz/faq/debian-rhel-centos-redhat-suse-hotplug-cpu/](http://www.cyberciti.biz/faq/debian-rhel-centos-redhat-suse-hotplug-cpu/).
