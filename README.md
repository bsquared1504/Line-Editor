# A Line Editor

`ed` is a line-oriented text editor. It is used to create, display, modify and
otherwise manipulate text files, both interactively and via shell scripts.
It was created, along with the Unix operating system, by Ken Thompson and
Dennis Ritchie. A detailed documentation of `ed` can be found is the
[GNU ed manual](https://www.gnu.org/software/ed/manual/ed_manual.html).

We will implement `ed` with some basic functionality as described in this tutorial:
https://www.howtoforge.com/linux-ed-command/ ([pdf](ed_tutorial.pdf))

## Objectives
* Use classes from the standard Java API by reading the specifications and
handling exceptions properly.
* Practice writing documentation for methods with PRE, TAS, and POS conditions.
* Practice writing test cases that sufficiently exercise methods under test.

## Requirements
The "main" class of this program is the `Editor` class, which should use
an object of the `java.util.LinkedList`
class from the standard Java API to keep track of the lines in a buffer.
The content of the buffer can be read from or written to a text file.
This `Editor` class should provides a list of interface methods for
displaying, appending, inserting, replacing, and deleting lines in the buffer.
The `Tester` class is the client program that uses a loop to prompt
users for commands and executes them by calling methods in the `Editor`
class. You need to finish implementing both classes to fulfill the requirements.
This `LinkedList` class has many methods; you will need to study the following
specification documentation carefully in order to choose the proper methods and
handle various exceptions properly.
* https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html

### Start `ed`
To launch the editor, you type `ed` in the command-line. With no argument, `ed`
will create a new buffer for you to write and show an empty line on the screen.
```
$ ed

```
If you start `ed` with a file name it will open the file, copy file content
to a buffer, and display the length of the file (number of characters).
```
$ ed test.txt
159

```

After `ed` is started, it allows users to edit the lines stored in the buffer.
`ed` treats the last line in the buffer as the `current` line.

### Supported Operations
Users should be able to interact with `ed` with the following single word commands:

`p`: print the current line.

`n`: print the current line with its line number (starting from 1).

`a`: append after the current line one or more lines from user command-line
input, which ends with a `.` in a separate line. The current line should
be set to the last line inserted.

`i`: insert one or more lines before the current line. The current line should
be set to the last line inserted.

`number`: "number" can be any integer. This command sets the current line to the line
corresponding to this number if possible. Otherwise, print `?` and leave the
current line `cursor` unchanged.

`d`: delete the current line if possible and move the current line cursor to the
next available line or -1 is no line if available.
